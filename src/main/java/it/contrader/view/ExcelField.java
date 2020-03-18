package it.contrader.view;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;

public class ExcelField extends AbstractView {
	
	private String phat;

	public void showResults(Request request) {

	}
	
	public void showOptions() {

		System.out.println("----- .:INSERIMENTO CAMPI:. ----");

		System.out.println(" Inserire il percorso file del file .xmlsx");
		this.phat = getInput();
		
		List<UserDTO> users = (List<UserDTO>) request.get("users");
		for (UserDTO u: users)
			System.out.println(u);
		System.out.println();

	}

	public void submit() {

		Request request = new Request();

		request.put("phat", phat);

		MainDispatcher.getInstance().callAction("Excel", "doControl", request);
	}
}
