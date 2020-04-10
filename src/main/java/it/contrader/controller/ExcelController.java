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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.contrader.dto.ExcelDTO;
import it.contrader.dto.ProductDTO;
import it.contrader.dto.UserDTO;
import it.contrader.service.ProductService;

@RestController
@RequestMapping("/excel")
@CrossOrigin(origins = "http://localhost:4200")
public class ExcelController {

	@Autowired
	private ProductService productService;
	
	private ExcelDTO excelDTO = new ExcelDTO();
	private String directory;

	private List<String> titleRead = new ArrayList<String>();
	private List<String> titleSelected = new ArrayList<String>();
	private Map<String, Integer> title_position = new HashMap<>();
	private Map<String, List<String>> title_data = new HashMap<>();
	private List<ProductDTO> productsList = new ArrayList<ProductDTO>();
	List<List<String>> stringList = new ArrayList<List<String>>();
	
	@PostMapping(value = "/preinsert")
	public StringDTO excelinser(@RequestBody String directory) {
		
		StringDTO temp = new StringDTO();
		
		this.directory = directory;
		stringList = new ArrayList<List<String>>();
		titleRead = new ArrayList<String>();

		try {
			stringList = readTitle();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		temp.setLines(titleRead);
		
		System.out.println(temp.getLines());
		return temp;//stringLinst

	}

	@PostMapping(value = "/insert")
	public Iterable<ProductDTO> insert(@RequestBody int productName, @RequestBody int price, @RequestBody int brand,
			@RequestBody int description) {

		List<String> titles = stringList.get(1);

		titleSelected.add(titles.get(productName - 1));
		titleSelected.add(titles.get(price - 1));
		titleSelected.add(titles.get(brand - 1));
		titleSelected.add(titles.get(description - 1));

		try {
			productsList = readTitleSelected();
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (ProductDTO u : productsList) {

			try {
				productService.insert(u);
			} catch (Exception e) { // ricevi questa exception se l'utente non è loggato

			}
		}

		return productService.getAll();
	}

	private Iterator<Row> openFile() throws Exception {

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
			throw new Exception();
		}

	}

	@SuppressWarnings("deprecation")
	public List<List<String>> readTitle() throws Exception {

		List<List<String>> insertData = new ArrayList<>();
		List<String> dataList = new ArrayList<>();

		Iterator<Row> itr = openFile();

		if (itr != null) {

			while (itr.hasNext()) {
				Row row = itr.next();
				Iterator<Cell> cellIterator = row.cellIterator(); // iterating over each column
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					if (cell.getAddress().getRow() == 0) {
						titleRead.add(cell.getStringCellValue().trim().toUpperCase()); // qui vengono aggiunti i
						title_position.put(cell.getStringCellValue().toUpperCase(), cell.getAddress().getColumn()); // salvo
																													// la
																													// posizione
																													// del
																													// titolo
																													// //
																													// titoli
																													// a
																													// lista
					} else if (cell.getAddress().getRow() <= 5) {
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
		} else
			return null;
	}

	@SuppressWarnings("deprecation")
	public List<ProductDTO> readTitleSelected() throws Exception {

		readTitle();

		int positionTitle1 = title_position.get(titleSelected.get(0)).intValue();
		int positionTitle2 = title_position.get(titleSelected.get(1)).intValue();
		int positionTitle3 = title_position.get(titleSelected.get(2)).intValue();
		int positionTitle4 = title_position.get(titleSelected.get(3)).intValue();

		List<String> list1 = new ArrayList<>();
		List<String> list2 = new ArrayList<>();
		List<String> list3 = new ArrayList<>();
		List<String> list4 = new ArrayList<>();

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
						if (counter == positionTitle1) {
							list1.add(cell.getStringCellValue());
						} else if (counter == positionTitle2) {
							list2.add(cell.getStringCellValue());
						} else if (counter == positionTitle3) {
							list3.add(cell.getStringCellValue());
						} else if (counter == positionTitle4) {
							list4.add(cell.getStringCellValue());
						}
						break;
					case Cell.CELL_TYPE_NUMERIC: // field that represents number cell type
						Double temp;
						if ((counter == positionTitle1)) {
							temp = cell.getNumericCellValue();
							list1.add(temp.toString());
						} else if (counter == positionTitle2) {
							temp = cell.getNumericCellValue();
							list2.add(temp.toString());
						} else if (counter == positionTitle3) {
							temp = cell.getNumericCellValue();
							list3.add(temp.toString());
						} else if (counter == positionTitle4) {
							temp = cell.getNumericCellValue();
							list4.add(temp.toString());
						}
						break;
					default:
					}
					counter++;
				}
			}
			String temp = list1.remove(0);
			title_data.put(temp.toUpperCase(), list1);
			temp = list2.remove(0);
			title_data.put(temp.toUpperCase(), list2);
			temp = list3.remove(0);
			title_data.put(temp.toUpperCase(), list3);
			temp = list4.remove(0);
			title_data.put(temp.toUpperCase(), list4);

		}

		createProducts();
		return getProductsList();
	}

	private List<ProductDTO> getProductsList() {
		return productsList;
	}

	public void createProducts() { // per logica del programma la lunghezza delle colonne deve essere ugale

		try {

			for (int i = 0; i < title_data.get(titleSelected.get(0)).size(); i++) {
				ProductDTO product = new ProductDTO();
				product.setProductName(title_data.get(titleSelected.get(0)).get(i));
				product.setPrice((int) Double.parseDouble(title_data.get(titleSelected.get(1)).get(i)));
				product.setDescription(title_data.get(titleSelected.get(2)).get(i));
				product.setBrand(title_data.get(titleSelected.get(3)).get(i));
				productsList.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
