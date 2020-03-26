package it.contrader.servlets;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import it.contrader.dto.ExcelDTO;
import it.contrader.dto.HistoryDTO;
import it.contrader.dto.ProductDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.Product;
import it.contrader.service.ExcelService;
import it.contrader.service.HistoryService;
import it.contrader.service.ProductService;
import it.contrader.service.Service;
import it.contrader.service.UserService;


public class ExcelServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	Service<ExcelDTO> excelService = new ExcelService();
	Service<HistoryDTO> historyService = new HistoryService();
	
	
	
	private ExcelDTO excelDTO = new ExcelDTO();
	private String directory;

	private List<String> titleRead = new ArrayList<String>();
	private List<String> titleSelected = new ArrayList<String>();
	private Map<String, Integer> title_position = new HashMap<>();
	private Map<String, List<String>> title_data = new HashMap<>();
	private List<Product> productsList = new ArrayList<Product>();
	
	public ExcelServlet() {
		
	}
	
	public void reset() {
		
		 titleRead = new ArrayList<String>();
		 titleSelected = new ArrayList<String>();
		 title_position = new HashMap<>();
		 title_data = new HashMap<>();
		 productsList = new ArrayList<Product>();
		//List<ProductDTO> listDTO = service.getAll();    //questo dovrebbe diventare una lista di prodotti letta dall'excel
		//request.setAttribute("list", listDTO);
	}
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String mode = request.getParameter("mode").toUpperCase();
		//String choice = (String) request.getParameter("choice"); non esiste in un contesto di webapplication
		
		ExcelDTO dto;
		
		if(request.getParameter("directory") != null) {
			directory = request.getParameter("directory").toString();;
		}
		
		String parUser1 = "";
		String parUser2 = "";
		String parUser3 = "";
		String parUser4 = "";
		
		switch(mode){
		
		case "INSERT":
			
			String title1 = "";
			String title2 = "";
			String title3 = "";
			String title4 = "";
			
			@SuppressWarnings("unchecked") List<String> titles = (List<String>)request.getSession().getAttribute("titlesList");
			try {
			parUser1 = request.getParameter("userPar1").toString();					//questo è in realtà un intero di selezione, proviene da ExcelInsertView
			parUser2 = request.getParameter("userPar2").toString();
			parUser3 = request.getParameter("userPar3").toString();
			parUser4 = request.getParameter("userPar4").toString();
			
			//excel.setDirectory(direc);
			//excel.readTitle();
			
			
			
			title1 = titles.get(Integer.parseInt(parUser1)-1);
			title2 = titles.get(Integer.parseInt(parUser2)-1);
			title3 = titles.get(Integer.parseInt(parUser3)-1);
			title4 = titles.get(Integer.parseInt(parUser4)-1);
			
			
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			
			
			//pulisce da tutti i titoli e aggiunge solo quelli selezionati dall'utente
			titles.clear();
			titles.add(title1);
			titles.add(title2);
			titles.add(title3);
			titles.add(title4);
			
			titleSelected = titles;
			//directory = (request.getParameter("directory").toString());    FORSE QUA PROBLEMA!!!!!!!!!!!!!!!!!!!!!!
			productsList = readTitleSelected(); //funziona
			
			
			
			excelDTO = new ExcelDTO(directory,title1,title2,title3,title4, productsList);
			
			System.out.println(productsList);
			System.out.println(directory);
			System.out.println(excelDTO.toString());
			
			//recupero la lista degli id dei prodotti inseriti
			List<Integer> idProductList = new ArrayList<Integer>();
			try {
				idProductList = excelService.insertExcel(excelDTO);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//raccolgo id dell'utente
			UserDTO userLogged = (UserDTO) request.getSession().getAttribute("user");
			int idLogged = userLogged.getId();
			//creo la lista dei record inseriti
			List<HistoryDTO> records = new ArrayList<HistoryDTO>();
			for(Integer i: idProductList) {
				HistoryDTO temp = new HistoryDTO(i.intValue(), idLogged);
				records.add(temp);
			}
			//inserisco la lista
			try {
				historyService.insertList(records);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//request = new Request();   qui se vuoi essere in logica pari devi creare un nuovo oggetto HTTPrequest	
			//request.put("mode", "mode");
			
			System.out.println("inserimento andato a buon fine");
			
			getServletContext().getRequestDispatcher("/homeuser.jsp").forward(request, response);
			break;
		
		case"GETCHOICE":
			//switch(choice.toUpperCase()) {

				
			//case "I":
				reset();
				if(directory.contains(".xlsx")) {
					
					excelDTO.setDirectory(directory);
					List<List<String>> stringList = readTitle();
					request.getSession().setAttribute("titlesList", stringList);
					//String temp = stringList.get(1).get(0);
					//request.getSession().setAttribute("unaStringa", titleRead.get(0));
					System.out.println(titleRead.get(0));
					getServletContext().getRequestDispatcher("/excel/excelinsert.jsp").forward(request, response);
				}else {
					request.setAttribute("ERROR", "file inserito non valido");
					getServletContext().getRequestDispatcher("/excel/excelmanager.jsp").forward(request, response);
				}
				//break;
			//default:
				//getServletContext().getRequestDispatcher("/excel/excelmanager.jsp").forward(request, response);
			
			//}
			break;
		default:
			getServletContext().getRequestDispatcher("/excel/excelmanager.jsp").forward(request, response);
			
		}
	}
	
	
	//gestione excel
	
	private Iterator<Row> openFile() {

		try {
			File file = new File(directory); // creating a new file instance
			System.out.println(directory);
			FileInputStream fis = new FileInputStream(file); // obtaining bytes from the file
			// creating Workbook instance that refers to .xlsx file
			XSSFWorkbook wb = new XSSFWorkbook(fis);

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
	public List<Product> readTitleSelected() {
		
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
	
	private List<Product> getProductsList() {
		return productsList;
	}


	public void createProducts() {    //per logica del programma la lunghezza delle colonne deve essere ugale
		
		
		
		try {
			
			for(int i = 0; i<title_data.get(titleSelected.get(0)).size(); i++) {
				Product product = new Product();
				product.setproductName(title_data.get(titleSelected.get(0)).get(i));
				product.setprice((int)Double.parseDouble(title_data.get(titleSelected.get(1)).get(i)));
				product.setDescription(title_data.get(titleSelected.get(2)).get(i));
				product.setProductBrand(title_data.get(titleSelected.get(3)).get(i));
				productsList.add(product);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	
}
