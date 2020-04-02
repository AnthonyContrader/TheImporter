package it.contrader.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import it.contrader.dao.HistoryRepository;
import it.contrader.dto.HistoryDTO;
import it.contrader.model.History;
import it.contrader.model.Product;
import it.contrader.model.User;

//stesso del converter

@Service
public class HistoryService extends AbstractService<History, HistoryDTO> {

	@Autowired
	private HistoryRepository historyRepository;
	

	public List<User> findByProductId(Long id) {
		
		return historyRepository.findByProductId(id);
	}
	
	public List<Product> findByUserId(Long id) {	//il prametro del model --> che conseguentemente è quello del db
		
		return historyRepository.findByUserId(id);
	}

}
