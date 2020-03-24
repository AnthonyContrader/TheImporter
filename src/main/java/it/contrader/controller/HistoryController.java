package it.contrader.controller;

import java.util.List;

import it.contrader.dto.HistoryDTO;
import it.contrader.dto.ProductDTO;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.model.Product;
import it.contrader.service.HistoryService;


/**
 * 
 * @author TheImporterBoys
 *
 *
 */
public class HistoryController implements Controller {

	/**
	 * definisce il pacchetto di vista user.
	 */
	private static String sub_package = "history.";
	
	private HistoryService historyService;
	/**
	 * Costruisce un oggetto di tipo UserService per poterne usare i metodi
	 */
	public HistoryController() {
		this.historyService = new HistoryService();
	}
	
	
	
	/**
	 * Metodo dell'interfaccia Controller. Estrae dalla request la mode
	 * (che riceve dalle view specifiche e pu� essere la richesta di una 
	 * scelta da parte dell'utente "GETCHOICE") e la scelta dell'utente.
	 * 
	 * Se la modalit� corrisponde ad una CRUD il controller chiama i service,
	 * altrimenti rimanda alla View della CRUD per richiedere i parametri
	 */
	@Override
	public void doControl(Request request) {
		
		//Estrae dalla request mode e choice
		String mode = (String) request.get("mode");
		
		String choice = (String) request.get("choice");

		//Definisce i campi della classe (serviranno sempre, tanto vale definirli una sola volta)
		int idRecord;
		int idUser;
		int idProduct;

		switch (mode) {
		// Arriva qui dalla UserReadView. Invoca il Service con il parametro id e invia alla UserReadView uno user da mostrare 
		case "READALL":
			List<HistoryDTO> historyDTOlist = historyService.getAll();
			request.put("historyDTOlist", historyDTOlist);
			MainDispatcher.getInstance().callView(sub_package + "HistoryRead", request);
			break;
		
		// Arriva qui dalla UserInsertView. Estrae i parametri da inserire e chiama il service per inserire uno user con questi parametri
		case "INSERT":
			idUser = Integer.parseInt(request.get("idUser").toString());
			idRecord = Integer.parseInt(request.get("idRecord").toString());
			idProduct = Integer.parseInt(request.get("idProduct").toString());
			
			//costruisce l'oggetto user da inserire
			HistoryDTO recordtoinsert = new HistoryDTO(idUser, idRecord, idProduct);
			//invoca il service
			historyService.insert(recordtoinsert);
			request = new Request();
			request.put("mode", "mode");
			//Rimanda alla view con la risposta
			MainDispatcher.getInstance().callView(sub_package + "HistoryInsert", request);
			break;
		
		
		//Arriva qui dalla UserView Invoca il Service e invia alla UserView il risultato da mostrare 
		case "SEARCHBYUSER":
			request.put("SEARCHBY", "user");
			idUser = Integer.parseInt(request.get("idUser").toString());
			List<ProductDTO> productHistory = historyService.searchByUserId(idUser);
			//Impacchetta la request con la lista degli user
			request.put("productHistory", productHistory);
			MainDispatcher.getInstance().callView("History", request);
			break;

		//Arriva qui dalla UserView Invoca il Service e invia alla UserView il risultato da mostrare 
		case "SEARCHBYPRODUCT":
			request.put("SEARCHBY", "product");
			idProduct = Integer.parseInt(request.get("idProduct").toString());
			List<UserDTO> userHistory = historyService.searchByProductId(idProduct);
			//Impacchetta la request con la lista degli user
			request.put("userHistory", userHistory);
			MainDispatcher.getInstance().callView("History", request);
			break;
			
		//Esegue uno switch sulla base del comando inserito dall'utente e reindirizza tramite il Dispatcher alla View specifica per ogni operazione
		//con REQUEST NULL (vedi una View specifica)
		case "GETCHOICE":
					
					//toUpperCase() mette in maiuscolo la scelta
			switch (choice.toUpperCase()) {
			
			case "P":
				MainDispatcher.getInstance().callView(sub_package + "HistoryShowByIdProduct", null);
				break;
				
			case "U":
				MainDispatcher.getInstance().callView(sub_package + "HistoryShowByIdUser", null);
				break;
				
			case "E":
				MainDispatcher.getInstance().callView("Login", null);
				break;

			case "B":
				MainDispatcher.getInstance().callView("HomeUser", null);
				break;
				
			default:
				MainDispatcher.getInstance().callView("Login", null);
			}
			
		default:
			MainDispatcher.getInstance().callView("Login", null);
		}
	}
}
