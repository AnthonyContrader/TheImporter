package it.contrader.service;

import java.util.List;

import it.contrader.converter.ProductConverter;
import it.contrader.converter.UserConverter;
import it.contrader.dao.ProductDAO;
import it.contrader.dto.ProductDTO;

/**
 * 
 * @author Vittorio, De Santis
 *
 *Grazie all'ereditariet� mi basta specificare i tipi di questa classe per
 *ereditare i metodi della clase AbstractService. Pertanto la classe risulta meno complicata
 *da scrivere, facendoci risparmiare tempo e fatica!
 */
public class ProductService {
	
	private ProductDAO ProductDAO;
	private ProductConverter ProductConverter;
	
	//Istanzio DAO  e Converter specifici.
	public ProductService(){
		this.ProductDAO = new ProductDAO();
		this.ProductConverter = new ProductConverter();
	}
	

	public List<ProductDTO> getAll() {
		// Ottiene una lista di entit� e le restituisce convertendole in DTO
		return ProductConverter.toDTOList(ProductDAO.getAll());
	}


	public ProductDTO read(int id) {
		// Ottiene un'entit� e la restituisce convertendola in DTO
		return ProductConverter.toDTO(ProductDAO.read(id));
	}


	public boolean insert(ProductDTO dto) {
		// Converte un DTO in entit� e lo passa al DAO per l'inserimento
		return ProductDAO.insert(ProductConverter.toEntity(dto));
	}


	public boolean update(ProductDTO dto) {
		// Converte un userDTO in entit� e lo passa allo userDAO per la modifica
		return ProductDAO.update(ProductConverter.toEntity(dto));
	}


	public boolean delete(int id) {
		// Questo mtodo chiama direttamente il DAO
		return ProductDAO.delete(id);
	}
	

}
