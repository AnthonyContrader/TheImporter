package it.contrader.controller;


import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.ExcelDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.model.Excel;
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
			
			try {
			parUser1 = request.get("userPar1").toString();					//questo è in realtà un intero di selezione, proviene da ExcelInsertView
			parUser2 = request.get("userPar2").toString();
			
			title1 = excel.getTitleRead().get(Integer.parseInt(parUser1));
			title2 = excel.getTitleRead().get(Integer.parseInt(parUser2));
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			ExcelDTO excelDTO = new ExcelDTO(directory,title1,title2);
			
			//sta roba è temporanea
			List<String> titleTemp = new ArrayList<String>();
			titleTemp.add(title1);
			titleTemp.add(title2);
			excel.setTitleSelected(titleTemp);
			excel.readTitleSelected();
			excel.createProducts();
			
			excelService.insert(excelDTO);
			
			request = new Request();
			
			request.put("mode", "mode");
			
			MainDispatcher.getInstance().callView(sub_package+"Product",null);		
			break;
		
		case"GETCHOICE":
			switch(choice.toUpperCase()) {

				
			case "I":
				excel.setDirectory(directory);
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
