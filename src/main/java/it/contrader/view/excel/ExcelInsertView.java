package it.contrader.view.excel;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class ExcelInsertView extends AbstractView {
	
	private Request request;
	private String choice;
	private final String mode = "INSERT";

	public ExcelInsertView() {
		
	}

	/**
	 * Mostra la lista utenti
	 */
	@Override
	public void showResults(Request request) {
		if (request != null) {
			/*System.out.println("\n------------------- Gestione utenti ----------------\n");
			System.out.println("ID\tUsername\tPassword\tTipo Utente");
			System.out.println("----------------------------------------------------\n");
			*/
			//@SuppressWarnings("unchecked")
			System.out.println("\n------------------- Titoli Letti ----------------\n");
			try {
			List<String> titles=(List<String>) request.get("titleList");
			
			for(String s: titles) {
				System.out.print(s+"\t\t");
			}
					}catch (Exception e) {
						e.getStackTrace();
					}

			/*List<ExcelDTO> users = (List<ExcelDTO>) request.get("par");
			for (ExcelDTO u: users)
				System.out.println(u);
			System.out.println();*/
		}
	}

	/**
	 * Chiede all'utente un input (lettera da tastiera) per la choice (vedi UserController). 
	 * Mette la modalità GETCHOICE nella mode.
	 */
	@Override
	public void showOptions() {
		System.out.println("          Scegli quale deve essere la prima colonna:");
		System.out.println("[1]  [2]");

		this.choice = getInput();
		
		
		
	}
	
	/**
	 * Impacchetta la request e la manda allo UserController.
	 */
	@Override
	public void submit() {
		request = new Request();
		request.put("choice", choice);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("User", "doControl", this.request);
	}

}
