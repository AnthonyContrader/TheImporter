package it.contrader.view.product;


import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;


public class ProductUpdateView extends AbstractView {
	private Request request;

	private int id;
	private String productName;
	private int price;
	
	private final String mode = "UPDATE";

	public ProductUpdateView() {
	}

	/**
	 * Se la request non � nulla (ovvero se si arriva dalla mode UPDATE del controller) mostra
	 * l'esito dell'operazione
	 */
	@Override
	public void showResults(Request request) {
		if (request!=null) {
			System.out.println("Modifica andata a buon fine.\n");
			MainDispatcher.getInstance().callView("Product", null);
		}
	}

	/**
	 * Chiede all'utente di inserire gli attributi dell'utente da modificare
	 */
	@Override
	public void showOptions() {
		try {
			System.out.println("Inserisci id del prodotto:");
			id = Integer.parseInt(getInput());
			System.out.println("Inserisci nome del prodotto:");
			productName = getInput();
			System.out.println("Inserisci prezzo prodotto:");
			price = Integer.parseInt(getInput());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Impacchetta la request con i dati inseriti nel metodo showOption()
	 */
	@Override
	public void submit() {
		request = new Request();
		request.put("id", id);
		request.put("productName", productName);
		request.put("price", price);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("Product", "doControl", request);
	}

}
