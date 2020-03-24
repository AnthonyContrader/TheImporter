package it.contrader.view;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.HistoryDTO;
import it.contrader.dto.ProductDTO;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;

public class HistoryView extends AbstractView{
	String choice;
	private Request request;
	@Override
	public void showResults(Request request) {
		// TODO Auto-generated method stub
		if(request!=null) {
			if(request.get("SEARCHBY").equals("product")){
				System.out.println("\n------------------- Gestione Ricerche ----------------\n");
				System.out.println("ID \t\t nomeUtente \t\t tipoUtente");
				System.out.println("----------------------------------------------------\n");
				
				List<UserDTO> histories =(List<UserDTO>) request.get("userHistory");
				for (UserDTO u: histories) {
					System.out.println(u.toString());
				}
				System.out.println();
				request.put("SEARCHBY", "reset");
			}
			if(request.get("SEARCHBY").equals("user")){
				
				System.out.println("\n------------------- Gestione Ricerche ----------------\n");
				System.out.println("ID \t\t nomeProdotto \t\t costo \t\t desc \t\t marca \t\t");
				System.out.println("----------------------------------------------------\n");
				
				
				List<ProductDTO> productHistory =(List<ProductDTO>) request.get("productHistory");
				
				for (ProductDTO u: productHistory) { 
					System.out.println(u.toString());
				}
				System.out.println();
				request.put("SEARCHBY", "reset");
			}
		}
	}

	@Override
	public void showOptions() {
		
		System.out.println("-------------MENU------------\n");
	     System.out.println(" Seleziona cosa vuoi cerare:");
	     System.out.println("[B]ack [E]xit ricerca per:[P]rodotto o [U]tente");
	     
	     choice = this.getInput();
		
	}

	@Override
	public void submit() {
		// TODO Auto-generated method stub
		request=new Request();
			switch (choice) {

			case "u":
				request.put("choice", choice);
				request.put("mode", "GETCHOICE");
				MainDispatcher.getInstance().callAction("History", "doControl", request);
				break;
		    case "p":
		    	request.put("choice", choice);
				request.put("mode", "GETCHOICE");
				MainDispatcher.getInstance().callAction("History", "doControl", request);
				break;
			 case "e":
		        	MainDispatcher.getInstance().callAction("Login", "doControl", null);
		        break;
			default:
				request.put("choice", choice);
				MainDispatcher.getInstance().callAction("Login", "doControl", request);
			}
	}

}
