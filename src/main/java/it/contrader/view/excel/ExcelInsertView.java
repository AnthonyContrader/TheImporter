package it.contrader.view.excel;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class ExcelInsertView extends AbstractView {
	
	private Request request;
	private String choice1, choice2, choice3, choice4;
	private final String mode = "INSERT";
	private List<String> titles;
	private List<String> preData;
	private List<List<String>> dataList;

	public ExcelInsertView() {
		
	}

	/**
	 * Mostra la lista utenti
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void showResults(Request request) {
		int counter;
		if (request != null) {
			System.out.println("\n------------------- Titoli Letti ----------------\n");
			try {
			dataList=(List<List<String>>) request.get("titlesList");//J:\TheImporter\TheImporter.xlsx
			titles=dataList.get(1);
			preData=dataList.get(0);
			
			counter = 0;
			for(String s: titles) {
				counter++;
				System.out.print("["+counter+"]"+s+"\t\t");
			}
			System.out.println(" ");
					}catch (Exception e) {
						e.getStackTrace();
					}
			counter=0;
			for(int i=0; i<= ((preData.size()/titles.size())-1);i++) {
				
				for(int j=0 ; j <= titles.size()-1;j++) {
					System.out.print(preData.get(counter)+"\t\t\t");
					counter++;
				}
				System.out.println(" ");
			}
		}
		System.out.println(" ");
		this.request = request;
	}

	/**
	 * Chiede all'utente un input (lettera da tastiera) per la choice (vedi UserController). 
	 * Mette la modalit� GETCHOICE nella mode.
	 */
	@Override
	public void showOptions() {
		System.out.println("          Scegli quale deve essere la prima colonna:");
		
		
	
		System.out.println("scegli un numero fra 1 e "+titles.size());

		this.choice1 = getInput();
		
		System.out.println("          Scegli quale deve essere la seconda colonna:");
		this.choice2 = getInput();
		
		System.out.println("          Scegli quale deve essere la terza colonna:");
		this.choice3 = getInput();
		
		System.out.println("          Scegli quale deve essere la quarta colonna:");
		this.choice4 = getInput();
	}
	
	/**
	 * Impacchetta la request e la manda allo UserController.
	 */
	@Override
	public void submit() {
		
		request.put("titlesList", titles);
		request.put("userPar1", choice1);
		request.put("userPar2", choice2);
		request.put("userPar3", choice3);
		request.put("userPar4", choice4);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("Excel", "doControl", this.request);
	}

}
