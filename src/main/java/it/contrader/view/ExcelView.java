package it.contrader.view;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;

public class ExcelView extends AbstractView{
	
	private String phat;

	public void showResults(Request request) {

	}
	
	public void showOptions() {

		System.out.println("----- .:INSERIMENTO FILE:. ----");

		System.out.println(" Inserire il percorso file del file .xmlsx");
		this.phat = getInput();

	}

	public void submit() {

		Request request = new Request();

		request.put("i", phat);

		MainDispatcher.getInstance().callAction("Excel", "doControl", request);
	}
}
