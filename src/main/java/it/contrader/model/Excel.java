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
	private Map<String, Integer> title_position = new HashMap<>();
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

	public Excel(String directory,String par1,String par2, List<Product> productList) {
		super();
		this.directory = directory;
		this.par1=par1;
		this.par2=par2;
		this.productsList = productList;
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
						title_position.put(cell.getStringCellValue().toUpperCase(),cell.getAddress().getColumn()); //salvo la posizione del titolo																// titoli a lista
					}
				}
			}
			return titleRead;
			}else return null;
		}

	public List<Product> readTitleSelected() {
		
		readTitle();
		
		int positionTitle1 = title_position.get(titleSelected.get(0)).intValue();
		int positionTitle2 = title_position.get(titleSelected.get(1)).intValue();
		
		List<String> list1=new ArrayList<>();
		List<String> list2=new ArrayList<>();
		
		
		
		List<String> data = new ArrayList<String>();
		String actualTitle = new String();
		
		Iterator<Row> itr = openFile();
		int check = 0;
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
	
	public void createProducts() {    //per logica del programma la lunghezza delle colonne deve essere ugale
		
		
		
		try {
			
			for(int i = 0; i<title_data.get(titleSelected.get(0)).size(); i++) {
				Product product = new Product();
				product.setproductName(title_data.get(titleSelected.get(0)).get(i));
				product.setprice((int)Double.parseDouble(title_data.get(titleSelected.get(1)).get(i)));
				//product.setprice(Integer.parseInt(title_data.get(titleSelected.get(0)).get(i)));
				productsList.add(product);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
