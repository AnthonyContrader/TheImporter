package it.contrader.view.product;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class ProductInsertView extends AbstractView{
	
	private Request request;
	private String productName;
	private int price;
	private final String mode = "INSERT";

	public ProductInsertView() {
	}
	
	/**
	 * Se la request non � nulla (ovvero se si arriva dalla mode INSERT del controller) mostra
	 * l'esito dell'operazione
	 */
	@Override
	public void showResults(Request request) {
		if (request!=null) {
			System.out.println("Inserimento andato a buon fine.\n");
			MainDispatcher.getInstance().callView("Product", null);
		}
	}

	/**
	 * Chiede all'utente di inserire gli attributi dell'utente da inserire
	 */
	@Override
	public void showOptions() {
			System.out.println("Inserisci il nome del prodotto:");
			productName = getInput();
			System.out.println("Inserisci il prezzo del prodotto:");
			price = Integer.parseInt(getInput());
			
	}

	/**
	 * Impacchetta la request con i dati inseriti nel metodo showOption()
	 */
	@Override
	public void submit() {
		request = new Request();
		request.put("productName", productName);
		request.put("price", price);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("Product", "doControl", request);
	}


}
