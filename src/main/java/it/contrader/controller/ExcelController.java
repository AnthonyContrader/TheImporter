package it.contrader.controller;


import it.contrader.dto.ExcelDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.model.Excel;
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
		
		String directory= request.get("directory").toString();;
		String parUser1;
		String parUser2;
		
		Excel excel=new Excel();
		
		switch(mode){
		
		case "INSERT":
			
			directory = request.get("directory").toString();
			parUser1 = request.get("parUser1").toString();
			parUser2 = request.get("parUser2").toString();
			
			ExcelDTO excelDTO = new ExcelDTO(directory,parUser1,parUser2);
			
			excelService.insert(excelDTO);
			
			request = new Request();
			
			request.put("mode", "mode");
			
			MainDispatcher.getInstance().callView(sub_package+"ExcelInsert",request);		
			break;
		
		case"GETCHOICE":
			switch(choice.toUpperCase()) {

				
			case "I":
				request.put("titlesList", excel.readTitle());
				MainDispatcher.getInstance().callView(sub_package + "ExcelInsert", request);
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
