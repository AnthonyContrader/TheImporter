package it.contrader.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.contrader.dto.HistoryDTO;
import it.contrader.dto.ProductDTO;
import it.contrader.dto.UserDTO;

import it.contrader.service.HistoryService;
import it.contrader.service.Service;


/**
 * 
 * @author TheImporterBoys
 *
 *
 */
public class HistoryServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
		
	private Service<HistoryDTO> historyService;
	
	public HistoryServlet() {
	
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
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		historyService = new HistoryService();
		//Estrae dalla request mode e choice
		String mode = (String) request.getParameter("mode");
		mode = mode.toUpperCase();
		String choice = (String) request.getParameter("choice");

		//Definisce i campi della classe (serviranno sempre, tanto vale definirli una sola volta)
		int idRecord;
		int idUser;
		int idProduct;

		switch (mode) {
		// Arriva qui dalla UserReadView. Invoca il Service con il parametro id e invia alla UserReadView uno user da mostrare 
		case "READALL":
			List<HistoryDTO> historyDTOlist = historyService.getAll();
			request.setAttribute("historyDTOlist", historyDTOlist);
			getServletContext().getRequestDispatcher("/history/historymanager.jsp").forward(request, response);
			break;
		
		
		case "INSERT":   //è stato brutalizzato probabilemte non funziona
			idUser = Integer.parseInt(request.getParameter("idUser").toString());
			idRecord = Integer.parseInt(request.getParameter("idRecord").toString());
			idProduct = Integer.parseInt(request.getParameter("idProduct").toString());
			
			//costruisce l'oggetto user da inserire
			HistoryDTO recordtoinsert = new HistoryDTO(idUser, idRecord, idProduct);
			//invoca il service
			historyService.insert(recordtoinsert);
			//request = new Request();
			//request.setAttribute("mode", "mode");
			//Rimanda alla view con la risposta
			getServletContext().getRequestDispatcher("/history/historymanager.jsp").forward(request, response);
			break;
		
		
		//Arriva qui dalla UserView Invoca il Service e invia alla UserView il risultato da mostrare 
		case "SEARCHBYUSER":
			request.setAttribute("SEARCHBY", "user");
			idUser = Integer.parseInt(request.getParameter("idUser").toString());
			List<ProductDTO> productHistory = historyService.searchByUserId(idUser);
			//Impacchetta la request con la lista degli user
			request.setAttribute("productHistory", productHistory);
			getServletContext().getRequestDispatcher("/history/historyrecord.jsp").forward(request, response);
			break;

		//Arriva qui dalla UserView Invoca il Service e invia alla UserView il risultato da mostrare 
		case "SEARCHBYPRODUCT":
			request.setAttribute("SEARCHBY", "product");
			idProduct = Integer.parseInt(request.getParameter("idProduct").toString());
			List<UserDTO> userHistory = historyService.searchByProductId(idProduct);
			//Impacchetta la request con la lista degli user
			request.setAttribute("userHistory", userHistory);
			getServletContext().getRequestDispatcher("/history/historyrecord.jsp").forward(request, response);
			break;
			
		case "MODE":	
			getServletContext().getRequestDispatcher("/history/historymanager.jsp").forward(request, response);
			
		//Esegue uno switch sulla base del comando inserito dall'utente e reindirizza tramite il Dispatcher alla View specifica per ogni operazione
		//con REQUEST NULL (vedi una View specifica)
		case "GETCHOICE":
					
					//toUpperCase() mette in maiuscolo la scelta
			switch (choice.toUpperCase()) {
			
			case "P":
				getServletContext().getRequestDispatcher("/history/historyrecord.jsp").forward(request, response);
				break;
				
			case "U":
				getServletContext().getRequestDispatcher("/history/historyrecord.jsp").forward(request, response);
				break;
				
			case "E":
				getServletContext().getRequestDispatcher("/history/historyrecord.jsp").forward(request, response);
				break;

			case "B":
				getServletContext().getRequestDispatcher("/history/historyrecord.jsp").forward(request, response);
				break;
				
			default:
				getServletContext().getRequestDispatcher("/history/historyrecord.jsp").forward(request, response);
			}
			
		default:
			getServletContext().getRequestDispatcher("/history/historyrecord.jsp").forward(request, response);
		}
	}
}
