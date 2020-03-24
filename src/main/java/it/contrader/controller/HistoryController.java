package it.contrader.controller;

import java.util.List;


import it.contrader.main.MainDispatcher;
import it.contrader.service.HistoryService;


/**
 * 
 * @author TheImporterBoys
 *
 *Si osservi che nel Controller compaiono solo oggetti del DTO e non del Model!
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
		case "READ":
			id = Integer.parseInt(request.get("id").toString());
			UserDTO userDTO = userService.read(id);
			request.put("user", userDTO);
			MainDispatcher.getInstance().callView(sub_package + "UserRead", request);
			break;
		
		// Arriva qui dalla UserInsertView. Estrae i parametri da inserire e chiama il service per inserire uno user con questi parametri
		case "INSERT":
			idUser = request.get("idUser").toString();
			idRecord = request.get("idRecord").toString();
			idProduct = request.get("idProduct").toString();
			
			//costruisce l'oggetto user da inserire
			UserDTO usertoinsert = new UserDTO(username, password, usertype);
			//invoca il service
			userService.insert(usertoinsert);
			request = new Request();
			request.put("mode", "mode");
			//Rimanda alla view con la risposta
			MainDispatcher.getInstance().callView(sub_package + "UserInsert", request);
			break;
		
		// Arriva qui dalla UserDeleteView. Estrae l'id dell'utente da cancellare e lo passa al Service
		case "DELETE":
			id = Integer.parseInt(request.get("id").toString());
			//Qui chiama il service
			userService.delete(id);
			request = new Request();
			request.put("mode", "mode");
			MainDispatcher.getInstance().callView(sub_package + "UserDelete", request);
			break;
		
		// Arriva qui dalla UserUpdateView
		case "UPDATE":
			id = Integer.parseInt(request.get("id").toString());
			username = request.get("username").toString();
			password = request.get("password").toString();
			usertype = request.get("usertype").toString();
			UserDTO usertoupdate = new UserDTO(username, password, usertype);
			usertoupdate.setId(id);
			userService.update(usertoupdate);
			request = new Request();
			request.put("mode", "mode");
			MainDispatcher.getInstance().callView(sub_package + "UserUpdate", request);
			break;
			
		//Arriva qui dalla UserView Invoca il Service e invia alla UserView il risultato da mostrare 
		case "USERLIST":
			List<UserDTO> usersDTO = userService.getAll();
			//Impacchetta la request con la lista degli user
			request.put("users", usersDTO);
			MainDispatcher.getInstance().callView("User", request);
			break;
			
		//Esegue uno switch sulla base del comando inserito dall'utente e reindirizza tramite il Dispatcher alla View specifica per ogni operazione
		//con REQUEST NULL (vedi una View specifica)
		case "GETCHOICE":
					
					//toUpperCase() mette in maiuscolo la scelta
			switch (choice.toUpperCase()) {
			
			case "L":
				MainDispatcher.getInstance().callView(sub_package + "UserRead", null);
				break;
				
			case "I":
				MainDispatcher.getInstance().callView(sub_package + "UserInsert", null);
				break;
				
			case "M":
				MainDispatcher.getInstance().callView(sub_package + "UserUpdate", null);
				break;
				
			case "C":
				MainDispatcher.getInstance().callView(sub_package + "UserDelete", null);
				break;
				
			case "E":
				MainDispatcher.getInstance().callView("Login", null);
				break;

			case "B":
				MainDispatcher.getInstance().callView("HomeAdmin", null);
				break;
				
			default:
				MainDispatcher.getInstance().callView("Login", null);
			}
			
		default:
			MainDispatcher.getInstance().callView("Login", null);
		}
	}
}
