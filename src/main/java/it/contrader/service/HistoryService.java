package it.contrader.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import it.contrader.converter.HistoryConverter;
import it.contrader.converter.ProductConverter;
import it.contrader.converter.UserConverter;
import it.contrader.dao.HistoryRepository;
import it.contrader.dto.HistoryDTO;
import it.contrader.dto.ProductDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.History;
import it.contrader.model.Product;
import it.contrader.model.User;

//stesso del converter



@Service
public class HistoryService extends AbstractService<History, HistoryDTO> {

	
	
	@Autowired
	private HistoryRepository historyRepository;
	
	
	@Autowired
	private HistoryConverter historyConverter;
	

	public HistoryDTO findByProduct(Product product) {
		
		return historyConverter.toDTO(historyRepository.findByProduct(product));
	}
	
	public HistoryDTO findByUser(User user) {	//il prametro del model --> che conseguentemente è quello del db
		
		return  historyConverter.toDTO(historyRepository.findByUser(user));
	}

}
