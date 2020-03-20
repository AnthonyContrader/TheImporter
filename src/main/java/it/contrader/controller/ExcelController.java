package it.contrader.controller;


import java.util.List;

import it.contrader.dto.ExcelDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.model.Excel;
import it.contrader.model.Product;
import it.contrader.service.ExcelService;


public class ExcelController implements Controller{

	
private static String sub_package = "excel.";
	
	private ExcelService excelService;
	private Excel excel;
	private String directory;
	
	public ExcelController() {
		this.excelService = new ExcelService();
		excel=new Excel();
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
			
			
			
			//sta roba è temporanea
			titles.clear();
			titles.add(title1);
			titles.add(title2);
			excel.setTitleSelected(titles);
			excel.setDirectory(request.get("directory").toString());
			List<Product> productList = excel.readTitleSelected();
			
			
			ExcelDTO excelDTO = new ExcelDTO(directory,title1,title2, productList);
			
			excelService.insert(excelDTO);
			
			request = new Request();
			
			request.put("mode", "mode");
			
			System.out.println("inserimento andato a buon fine");
			
			MainDispatcher.getInstance().callView("HomeUser",null);		
			break;
		
		case"GETCHOICE":
			switch(choice.toUpperCase()) {

				
			case "I":
				if(directory.contains(".xlsx")) {excel.setDirectory(directory);
				request.put("titlesList", excel.readTitle());
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

}
