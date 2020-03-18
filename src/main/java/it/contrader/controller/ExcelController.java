package it.contrader.controller;

import java.util.List;

import it.contrader.dto.ExcelDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.service.ExcelService;


public class ExcelController {

	
private static String sub_package = "product.";
	
	private ExcelService excelService;
	
	public ExcelController() {
		this.excelService = new ExcelService();
	}
	
	
	public void doControl(Request request) {
		
        String mode = (String) request.get("mode");
		
		String choice = (String) request.get("choice");
		
		String directory;
		String parUser1;
		String parUser2;
		
		
		switch(mode){
		
		case "INSERT":
			
			directory = request.get("directory").toString();
			parUser1 = request.get("parUser1").toString();
			parUser2 = request.get("parUser2").toString();
			
			ExcelDTO excelDTO = new ExcelDTO(directory,parUser1,parUser2);
			
			productService.insert(productToInsert);
			
			request=new Request();
			
			request.put("mode", "mode");
			
			MainDispatcher.getInstance().callView(sub_package+"ExcelInsert",request);		
			break;
		
		case"GETCHOICE":
			switch(choice.toUpperCase()) {
			case "L":
				MainDispatcher.getInstance().callView(sub_package + "ProductRead", null);
				break;
				
			case "I":
				MainDispatcher.getInstance().callView(sub_package + "ProductInsert", null);
				break;
				
			case "M":
				MainDispatcher.getInstance().callView(sub_package + "ProductUpdate", null);
				break;
				
			case "C":
				MainDispatcher.getInstance().callView(sub_package + "ProductDelete", null);
				break;
				
			case "E":
				MainDispatcher.getInstance().callView("Login", null);
				break;

			case "B":
				MainDispatcher.getInstance().callView("HomeUser", null);
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
