package it.contrader.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.dto.ExcelDTO;
import it.contrader.dto.ProductDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.Product;
import it.contrader.service.HistoryService;
import it.contrader.service.ProductService;

@Controller
@RequestMapping("/excel")
public class ExcelController {
	
	private ExcelDTO excelDTO = new ExcelDTO();
	private String directory;

	private List<String> titleRead = new ArrayList<String>();
	private List<String> titleSelected = new ArrayList<String>();
	private Map<String, Integer> title_position = new HashMap<>();
	private Map<String, List<String>> title_data = new HashMap<>();
	private List<ProductDTO> productsList = new ArrayList<ProductDTO>();
	List<List<String>> stringList= new ArrayList<List<String>>(); 

	@Autowired
	private ProductService productService;

	@Autowired
	private HistoryService historyService;
	
	@PostMapping("/preinsert")
	public String preinsert(HttpServletRequest request, @RequestParam("directory") String directory) {
		if(directory.contains(".xlsx")) {
			
			this.directory = directory;
			stringList = readTitle(); //leggo la lista di liste da passare alla jsp come preview
			request.getSession().setAttribute("titlesList", stringList);
			return "excelinsert"; // completa l'inserimento da cambiare

		}else {
			System.out.println("directory errata");
			return "excelmanager"; //ritorna indietro da cambiare
		}
	}



	@PostMapping("/insert")
	public String insert(HttpServletRequest request, @RequestParam("productName") int productName,
			@RequestParam("price") int price, @RequestParam("brand") int brand, @RequestParam("description") int description) {//gli interi rapresentano i nummeri delle colonne associate
		
		UserDTO userDTO = (UserDTO)request.getSession().getAttribute("user"); //raccolgo l'utente loggato
		
		List<String>titles= stringList.get(1);
		
		titleSelected.add(titles.get(productName-1));
		titleSelected.add(titles.get(price-1));
		titleSelected.add(titles.get(brand-1));
		titleSelected.add(titles.get(description-1));
		
		productsList = readTitleSelected();
		System.out.println(productsList);
		for (ProductDTO u: productsList) {
			
			productService.insertWRecord(userDTO, u);
			
		}

		return "redirect:/product/getall";
	}



	
	
	
	
	
	
	//gestione excel
	
		private Iterator<Row> openFile() {

			try {
				File file = new File(directory); // creating a new file instance
				FileInputStream fis = new FileInputStream(file); // obtaining bytes from the file
				// creating Workbook instance that refers to .xlsx file
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				wb.close();
				XSSFSheet sheet = wb.getSheetAt(0); // creating a Sheet object to retrieve object
				Iterator<Row> itr = sheet.iterator(); // iterating over excel file

				return itr;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}

		}
		
		@SuppressWarnings("deprecation")
		public List<List<String>> readTitle() {

			List<List<String>> insertData=new ArrayList<>();
			List<String> dataList=new ArrayList<>();

			Iterator<Row> itr = openFile();


			if (itr != null) {

				while (itr.hasNext()) {
					Row row = itr.next();
					Iterator<Cell> cellIterator = row.cellIterator(); // iterating over each column
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						if (cell.getAddress().getRow() == 0) {
							titleRead.add(cell.getStringCellValue().trim().toUpperCase()); // qui vengono aggiunti i
							title_position.put(cell.getStringCellValue().toUpperCase(),cell.getAddress().getColumn()); //salvo la posizione del titolo																// titoli a lista
						}else if( cell.getAddress().getRow() <= 5 ){
							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_STRING: // field that represents string cell type
								dataList.add(cell.getStringCellValue());
								break;
							case Cell.CELL_TYPE_NUMERIC: // field that represents number cell type
								dataList.add(Double.toString(cell.getNumericCellValue()));
								break;
							default:
								dataList.add("x");
							break;
							}

						}
						
					}
				}
				insertData.add(dataList);
				insertData.add(titleRead);
				return insertData;
				}else return null;
			}

		@SuppressWarnings("deprecation")
		public List<ProductDTO> readTitleSelected() {
			
			readTitle();
			
			int positionTitle1 = title_position.get(titleSelected.get(0)).intValue();
			int positionTitle2 = title_position.get(titleSelected.get(1)).intValue();
			int positionTitle3 = title_position.get(titleSelected.get(2)).intValue();
			int positionTitle4 = title_position.get(titleSelected.get(3)).intValue();
			
			List<String> list1=new ArrayList<>();
			List<String> list2=new ArrayList<>();
			List<String> list3=new ArrayList<>();
			List<String> list4=new ArrayList<>();
			
			Iterator<Row> itr = openFile();
			if (itr != null) {
				while (itr.hasNext()) {
					Row row = itr.next();
					Iterator<Cell> cellIterator = row.cellIterator(); // iterating over each column
					int counter = 0;
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_STRING: // field that represents string cell type
							if(counter == positionTitle1) {
								list1.add(cell.getStringCellValue());
							}
							else if(counter == positionTitle2) {
								list2.add(cell.getStringCellValue());
							}else if(counter == positionTitle3) {
								list3.add(cell.getStringCellValue());
							}else if(counter == positionTitle4) {
								list4.add(cell.getStringCellValue());
							}
							break;
						case Cell.CELL_TYPE_NUMERIC: // field that represents number cell type
							Double temp;
							if ((counter == positionTitle1)) {
								temp=cell.getNumericCellValue();
								list1.add(temp.toString());
							}else if(counter==positionTitle2) {
								temp=cell.getNumericCellValue();
								list2.add(temp.toString());
							}else if(counter == positionTitle3) {
								temp=cell.getNumericCellValue();
								list3.add(temp.toString());
							}else if(counter == positionTitle4) {
								temp=cell.getNumericCellValue();
								list4.add(temp.toString());
							}
							break;
						default:
						}
						counter++;
					}
				}
				String temp=list1.remove(0);
				title_data.put(temp.toUpperCase(), list1);
				temp=list2.remove(0);
				title_data.put(temp.toUpperCase(), list2);
				temp=list3.remove(0);
				title_data.put(temp.toUpperCase(), list3);
				temp=list4.remove(0);
				title_data.put(temp.toUpperCase(), list4);
				
			}
			
			createProducts();
			return getProductsList();
		}
		
		private List<ProductDTO> getProductsList() {
			return productsList;
		}


		public void createProducts() {    //per logica del programma la lunghezza delle colonne deve essere ugale
			
			
			
			try {
				
				for(int i = 0; i<title_data.get(titleSelected.get(0)).size(); i++) {
					ProductDTO product = new ProductDTO();
					product.setProductName(title_data.get(titleSelected.get(0)).get(i));
					product.setPrice((int)Double.parseDouble(title_data.get(titleSelected.get(1)).get(i)));
					product.setDescription(title_data.get(titleSelected.get(2)).get(i));
					product.setBrand(title_data.get(titleSelected.get(3)).get(i));
					productsList.add(product);
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}

}
