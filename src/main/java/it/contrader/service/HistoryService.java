package it.contrader.service;

import java.sql.SQLException;
import java.util.List;

import it.contrader.converter.HistoryConverter;
import it.contrader.converter.ProductConverter;
import it.contrader.converter.UserConverter;
import it.contrader.dao.HistoryDAO;
import it.contrader.dto.HistoryDTO;
import it.contrader.dto.ProductDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.History;


public class HistoryService extends AbstractService<History, HistoryDTO>{
	private HistoryDAO historyDAO;
	private HistoryConverter historyConverter;
	private ProductConverter productConverter;
	private UserConverter userConverter;
	
	public HistoryService(){
		this.historyDAO = new HistoryDAO();
		this.historyConverter = new HistoryConverter();
		this.productConverter = new ProductConverter();
		this.userConverter = new UserConverter();
	}
	
	public boolean insertList(List<HistoryDTO> recordList) throws SQLException {
		return historyDAO.insertList(historyConverter.toEntityList(recordList));
	}
	
	public boolean insert(HistoryDTO dto) { //ok
		// Converte un DTO in entit� e lo passa al DAO per l'inserimento
		//return ExcelDAO.insert(ExcelConverter.toEntity(dto).getProductsList());
		return historyDAO.insert(historyConverter.toEntity(dto));
	}
	
	public List<ProductDTO> searchByUserId(int idUser) {
		return productConverter.toDTOList(historyDAO.searchByUserId(idUser));
	}
	
	public List<UserDTO> searchByProductId(int idProduct) {
		return userConverter.toDTOList(historyDAO.searchByProductId(idProduct));
	}
	
	public List<HistoryDTO> getAll(){
		return historyConverter.toDTOList(historyDAO.getAll());
	}
}
