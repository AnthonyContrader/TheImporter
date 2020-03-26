package it.contrader.service;

import java.sql.SQLException;
import java.util.List;

import it.contrader.dto.ExcelDTO;
import it.contrader.dto.HistoryDTO;
import it.contrader.dto.ProductDTO;
import it.contrader.dto.UserDTO;

/**
 * 
 * @author Vittorio
 *
 * @param <DTO> questo � un tipo generico!
 */
public interface Service<DTO> {
	/**
	 * Segue la firma dei metodi da implementare nella classe AbstractService
	 */
	public List<DTO> getAll();
	
	public DTO read(int id);
	
	public boolean insert(DTO dto);
	
	public boolean update(DTO dto);
	
	public boolean delete(int id);


	public List<Integer> insertExcel(DTO dto) throws SQLException;

	public boolean insertList(List<HistoryDTO> records) throws SQLException;

	public List<UserDTO> searchByProductId(int idProduct);

	public List<ProductDTO> searchByUserId(int idUser);

	

}
