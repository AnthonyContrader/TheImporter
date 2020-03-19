package it.contrader.model;

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

public class Excel {

	private String directory;
	private String par1, par2; // questi sono gli attributi di prodotto
	
	private List<String> titleRead = new ArrayList<String>();
	

	private List<String> titleSelected = new ArrayList<String>();
	private Map<String, List<String>> title_data = new HashMap<>();
	private List<Product> productsList = new ArrayList<Product>();

	
	public List<String> getTitleRead() {
		return titleRead;
	}
	
	public List<Product> getProductsList() {
		
		return productsList;
	}
	
	public List<String> getTitleSelected() {
		return titleSelected;
	}

	public void setTitleSelected(List<String> titleSelected) {
		this.titleSelected = titleSelected;
	}

	public Excel() {
	}

	public Excel(String directory,String par1,String par2) {
		super();
		this.directory = directory;
		this.par1=par1;
		this.par2=par2;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}
	
	public void setPar1(String title1) {
		par1 = title1;
	}
	
	public void setPar2(String title2) {
		par2 = title2;
	}
	
	public String getPar1() {
		return par1;
	}
	public String getPar2() {
		return par2;
	}

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

	public List<String> readTitle() {

		Iterator<Row> itr = openFile();

		if (itr != null) {

			while (itr.hasNext()) {
				Row row = itr.next();
				Iterator<Cell> cellIterator = row.cellIterator(); // iterating over each column
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					if (cell.getAddress().getRow() == 0) {
						titleRead.add(cell.getStringCellValue().trim().toUpperCase()); // qui vengono aggiunti i
																						// titoli a lista
					}
				}
			}
			return titleRead;
			}else return null;
		}

	public void readTitleSelected() {

		System.out.println(directory);
		System.out.println(titleSelected);
		
		
		List<String> data = new ArrayList<String>();
		String actualTitle = new String();
		
		Iterator<Row> itr = openFile();
		int check = 0;
		if (itr != null) {
			while (itr.hasNext()) {
				Row row = itr.next();
				Iterator<Cell> cellIterator = row.cellIterator(); // iterating over each column
				check = 0; // resetto per il prossimo titolo
				do {
					Cell cell;
					if (cellIterator.hasNext()) {
						cell = cellIterator.next();
					} 
					else {
						break;
					}
					
					System.out.println(cell.getStringCellValue());
					if (cell.getAddress().getRow() == 0 && titleSelected.contains(cell.getStringCellValue())) { // controla che il titolo sia presente nei selezionati
						actualTitle = cell.getStringCellValue();
						check = 1;
						System.out.println(actualTitle);
					} else if(check == 1) {
						data.add(cell.getStringCellValue());
						System.out.println(data);
					}
				} while (check == 1);
				
				if(check == 1) {                        //se ho trovato un titolo corretto aggiungo i dati all'hashmap
					title_data.put(actualTitle, data);
				}
			}
		}
		System.out.println(title_data+"laREad");
	}
	
	public void createProducts() {    //per logica del programma la lunghezza delle colonne deve essere ugale
		
		Product product = new Product();
		
		try {
			
			System.out.println(title_data+"crateProduct");
			
			for(int i = 0; i<title_data.get(par1).size(); i++) {
				product.setproductName(title_data.get(par1).get(i));
				product.setprice(Integer.parseInt(title_data.get(par2).get(i)));
				productsList.add(product);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
