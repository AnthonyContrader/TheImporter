package it.contrader.view;


import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;

public class HomeUserView extends AbstractView{

	String choice;
	private Request request;

	@Override
	public void showResults(Request request) {
		//System.out.println("\n-----Purtroppo in questo sample l'utente non pu� fare nulla, ci scusiamo per il disagio.-----");
		if(request!=null) {try {
			System.out.println("\n Benvenuto in SAMPLE PROJECT "+request.get("username").toString() + "\n");
		    }catch(Exception e) {}
			try {
			System.out.println("\n ERRORE INSERIMENTO FILE : "+request.get("ERROR").toString() + "\n");
		    }catch(Exception e) {}
	    	}

	}

	@Override
	public void showOptions() {
		 System.out.println("-------------MENU------------\n");
	     System.out.println(" Seleziona cosa vuoi gestire:");
	     System.out.println("[P]rodotti  [E]sci [I]nporta File [H]istory");
	     
	     choice = this.getInput();

	}

	@Override
	public void submit() {
        request = new Request();
		switch (choice) {

		case "p":
			this.request.put("mode", "PRODUCTLIST");
			MainDispatcher.getInstance().callAction("Product", "doControl", request);
			break;
	    case "i":
	    	
			MainDispatcher.getInstance().callView("Excel", null);
			break;
		 case "e":
	        	MainDispatcher.getInstance().callAction("Login", "doControl", null);
	        break;
		 case "h":
	        	MainDispatcher.getInstance().callView("History",null);
	        break;
		default:
			request.put("choice", choice);
			MainDispatcher.getInstance().callAction("Login", "doControl", request);
		}
	}

}
