package it.contrader.view;


import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;

public class HomeUserView extends AbstractView{

	String choice;
	private Request request;

	@Override
	public void showResults(Request request) {
		//System.out.println("\n-----Purtroppo in questo sample l'utente non puà fare nulla, ci scusiamo per il disagio.-----");
		if(request!=null) {
	    	System.out.println("\n Benvenuto in SAMPLE PROJECT "+request.get("username").toString() + "\n");
	    	}

	}

	@Override
	public void showOptions() {
		/*System.out.println("-------------MENU------------\n");
		System.out.println("NESSUNA OPZIONE DISPONIBILE!");
		System.out.println("\n Esatto, puoi solo uscire...");
		choice = this.getInput();*/
		 System.out.println("-------------MENU------------\n");
	     System.out.println(" Seleziona cosa vuoi gestire:");
	     System.out.println("[P]rodotti  [E]sci");
	        //Il metodo che salva l'input nella stringa choice.
	        //getInput() è definito in AbstractView.
	     choice = this.getInput();

	}

	@Override
	public void submit() {

		switch (choice) {

		case "p":
			MainDispatcher.getInstance().callAction("Product", "doControl", null);
			break;
		 case "e":
	        	MainDispatcher.getInstance().callAction("Login", "doControl", null);
	        break;
		default:
			request.put("choice", choice);
			MainDispatcher.getInstance().callAction("Login", "doControl", null);
		}
	}

}
