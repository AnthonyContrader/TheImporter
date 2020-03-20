package it.contrader.controller;


import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import it.contrader.dto.ExcelDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.model.Product;
import it.contrader.service.ExcelService;


public class ExcelController implements Controller{

	
private static String sub_package = "excel.";
	
	private ExcelService excelService;
	private ExcelDTO excelDTO = new ExcelDTO();
	private String directory;

	private List<String> titleRead = new ArrayList<String>();
	private List<String> titleSelected = new ArrayList<String>();
	private Map<String, Integer> title_position = new HashMap<>();
	private Map<String, List<String>> title_data = new HashMap<>();
	private List<Product> productsList = new ArrayList<Product>();
	
	public ExcelController() {
		this.excelService = new ExcelService();
	}
	
	
	public void doControl(Request request) {
		
        String mode = (String) request.get("mode");
		
		String choice = (String) request.get("choice");
		
		if(request.get("directory") != null) {
			directory = request.get("directory").toString();;
		}
		
		String parUser1 = "";
		String parUser2 = "";
		
		
		
		
		switch(mode){
		
		case "INSERT":
			
			String title1 = "";
			String title2 = "";
			
			@SuppressWarnings("unchecked") List<String> titles = (List<String>)request.get("titlesList");
			
			try {
			parUser1 = request.get("userPar1").toString();					//questo è in realtà un intero di selezione, proviene da ExcelInsertView
			parUser2 = request.get("userPar2").toString();
			
			//excel.setDirectory(direc);
			//excel.readTitle();
			
			
			
			title1 = titles.get(Integer.parseInt(parUser1)-1);
			title2 = titles.get(Integer.parseInt(parUser2)-1);
			
			
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			
			
			
			titles.clear();
			titles.add(title1);
			titles.add(title2);
			
			titleSelected = titles;
			directory = (request.get("directory").toString());
			productsList = readTitleSelected();
			
			
			excelDTO = new ExcelDTO(directory,title1,title2, productsList);
			
			excelService.insert(excelDTO);
			
			request = new Request();
			
			request.put("mode", "mode");
			
			System.out.println("inserimento andato a buon fine");
			
			MainDispatcher.getInstance().callView("HomeUser",null);		
			break;
		
		case"GETCHOICE":
			switch(choice.toUpperCase()) {

				
			case "I":
				if(directory.contains(".xlsx")) {excelDTO.setDirectory(directory);
				request.put("titlesList", readTitle());
				MainDispatcher.getInstance().callView(sub_package + "ExcelInsert", request);
				}else {
					request.put("ERROR", "file inserito non valido");
					MainDispatcher.getInstance().callView("HomeUser",request);
				}
				break;
			default:
				MainDispatcher.getInstance().callView("Login", null);
			
			}
			break;
		default:
			MainDispatcher.getInstance().callView("Login", null);
			
		}
	}
	
	
	//gestione excel
	
	private Iterator<Row> openFile() {
		try {
			File file = new File(directory); // creating a new file instance
			FileInputStream fis = new FileInputStream(file); // obtaining bytes from the file
			// creating Workbook instance that refers to .xlsx file
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0); // creating a Sheet object to retrieve object
			wb.close();
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
						titleRead .add(cell.getStringCellValue().trim().toUpperCase()); // qui vengono aggiunti i
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
	public List<Product> readTitleSelected() {
		
		readTitle();
		
		int positionTitle1 = title_position.get(titleSelected.get(0)).intValue();
		int positionTitle2 = title_position.get(titleSelected.get(1)).intValue();
		
		List<String> list1=new ArrayList<>();
		List<String> list2=new ArrayList<>();
		
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
			
		}
		
		createProducts();
		return getProductsList();
	}
	
	private List<Product> getProductsList() {
		return productsList;
	}


	public void createProducts() {    //per logica del programma la lunghezza delle colonne deve essere ugale
		
		
		
		try {
			
			for(int i = 0; i<title_data.get(titleSelected.get(0)).size(); i++) {
				Product product = new Product();
				product.setproductName(title_data.get(titleSelected.get(0)).get(i));
				product.setprice((int)Double.parseDouble(title_data.get(titleSelected.get(1)).get(i)));
				productsList.add(product);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	
}
