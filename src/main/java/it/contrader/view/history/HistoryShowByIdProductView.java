package it.contrader.view.history;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class HistoryShowByIdProductView extends AbstractView {
	private Request request;
	private String choice;

	@Override
	public void showResults(Request request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showOptions() {
		
	     System.out.println("inserire l'id del prodotto da cercare:");
	     choice = this.getInput();
		
	}

	@Override
	public void submit() {
		request=new Request();
		request.put("SEARCHBYPRODUCT", choice);
		MainDispatcher.getInstance().callAction("History", "doControl", request);
		
	}

}
