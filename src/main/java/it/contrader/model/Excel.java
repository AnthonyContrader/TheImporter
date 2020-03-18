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

	private List<String> titleRead = new ArrayList<String>();
	private List<String> titleSelected = new ArrayList<String>();
	private Map<String, String> title_data = new HashMap<>();

	public Excel() {
	}

	public Excel(String directory) {
		super();
		this.directory = directory;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
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
					} else
						break;
					String actualTitle;
					if (cell.getAddress().getRow() == 0 && titleSelected.contains(cell.getStringCellValue())) { // controla che il titolo sia presente nei selezionati
						actualTitle = cell.getStringCellValue();
						check = 1;
					} else
						break;

					title_data.put(actualTitle, cell.getStringCellValue());

				} while (check == 1);
			}
		}
	}
}
