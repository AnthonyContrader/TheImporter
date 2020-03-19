package it.contrader.view.excel;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class ExcelInsertView extends AbstractView {
	
	private Request request;
	private String choice1, choice2;
	private final String mode = "INSERT";
	private List<String> titles;

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
			titles=(List<String>) request.get("titlesList");
			
			int counter = 0;
			for(String s: titles) {
				counter++;
				System.out.print("["+counter+"]"+s+"\t\t");
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
	 * Mette la modalit� GETCHOICE nella mode.
	 */
	@Override
	public void showOptions() {
		System.out.println("          Scegli quale deve essere la prima colonna:");
		
		
	
		System.out.println("scegli un numero fra 1 e"+titles.size());

		this.choice1 = getInput();
		
		System.out.println("          Scegli quale deve essere la seconda colonna:");
		this.choice2 = getInput();
	}
	
	/**
	 * Impacchetta la request e la manda allo UserController.
	 */
	@Override
	public void submit() {
		request = new Request();
		request.put("userPar1", choice1);
		request.put("userPar2", choice2);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("Excel", "doControl", this.request);
	}

}
