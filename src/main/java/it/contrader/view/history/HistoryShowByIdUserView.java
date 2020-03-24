package it.contrader.view.history;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class HistoryShowByIdUserView extends AbstractView{

	private Request request;
	private String choice;

	@Override
	public void showResults(Request request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showOptions() {
		// TODO Auto-generated method stub
		System.out.println("inserire l'id dell' utente da cercare:");
	     choice = this.getInput();
	}

	@Override
	public void submit() {
		// TODO Auto-generated method stub
		request=new Request();
		request.put("SEARCHBYUSER", choice);
		MainDispatcher.getInstance().callAction("History", "doControl", request);
	}

}
