package it.contrader.view;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;

public class ExcelView extends AbstractView{
	
	private String directory;

	public void showResults(Request request) {

	}
	
	public void showOptions() {

		System.out.println("----- .:INSERIMENTO FILE:. ----");

		System.out.println(" Inserire il percorso file del file .xmlsx");
		this.directory = getInput();

	}

	public void submit() {

		Request request = new Request();

		request.put("directory", directory);
		request.put("mode", "GETCHOICE");
		request.put("choice", "I");
		
		

		MainDispatcher.getInstance().callAction("Excel", "doControl", request);
	}
}
