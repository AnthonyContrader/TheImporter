package it.contrader.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import it.contrader.converter.ProductConverter;
import it.contrader.dao.ProductRepository;
import it.contrader.dto.HistoryDTO;
import it.contrader.dto.ProductDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.Product;

@Service
public class ProductService extends AbstractService<Product, ProductDTO> {

	@Autowired
	private ProductConverter converter;
	@Autowired
	private ProductRepository repository;
	@Autowired
	private HistoryService historyService;
	
	public ProductDTO getById(long longValue) {
		return converter.toDTO(repository.getProductById(longValue));
	}

	public Long insertWRecord(UserDTO userDTO, ProductDTO productDTO) {
		ProductDTO insertedProduct = converter.toDTO(repository.save(converter.toEntity(productDTO))); //salva nel db product, poi converte per raccogliere il DTO con k'id inserito nel db
		
		HistoryDTO historyDTO = new HistoryDTO();
		historyDTO.setProductDTO(insertedProduct);
		historyDTO.setUserDTO(userDTO);
		
		return historyService.insert(historyDTO).getId(); //ritorna l'id del record inserito
		
	}
	
	public Long deleteWRecord(Long productId) {
		
		long result = -1;
		
		for(HistoryDTO h: historyService.getAll()) {
			if(h.getProductDTO().getId() == productId) {
				historyService.delete(h.getId());
				return h.getId();
			}
		}
		
		return result;
	}

}
