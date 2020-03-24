package it.contrader.service;

import java.util.List;

import it.contrader.converter.HistoryConverter;
import it.contrader.dao.HistoryDAO;
import it.contrader.dto.HistoryDTO;
import it.contrader.model.History;
import it.contrader.model.Product;
import it.contrader.model.User;

public class HistoryService {
	private HistoryDAO historyDAO;
	private HistoryConverter historyConverter;
	
	public HistoryService(){
		this.historyDAO = new HistoryDAO();
		this.historyConverter = new HistoryConverter();
	}
	
	public boolean insert(HistoryDTO dto) {
		// Converte un DTO in entit� e lo passa al DAO per l'inserimento
		//return ExcelDAO.insert(ExcelConverter.toEntity(dto).getProductsList());
		return historyDAO.insert(historyConverter.toEntity(dto));
	}
	
	public List<Product> serchByUserId(HistoryDTO dto) {
		return historyDAO.searchByUserId(historyConverter.toEntity(dto).getIdUser());
	}
	
	public List<User> serchByProductId(HistoryDTO dto) {
		return historyDAO.searchByProductId(historyConverter.toEntity(dto).getIdProduct());
	}
	
	public List<History> getAll(){
		return historyDAO.getAll();
	}
}
