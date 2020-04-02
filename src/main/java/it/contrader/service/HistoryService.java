package it.contrader.service;

import java.util.ArrayList;
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

//stesso del converter

@Service
public class HistoryService extends AbstractService<History, HistoryDTO> {

	@Autowired
	private HistoryConverter historyConverter;
	
	@Autowired
	private UserConverter userConverter;
	
	@Autowired
	private ProductConverter productConverter;
	
	@Autowired
	private HistoryRepository historyRepository;
	

	public List<UserDTO> findByProductId(Long productId) {
		
		return userConverter.toDTOList(historyRepository.findByProductId(productId));
	}
	
	public List<ProductDTO> findByUserId(Long userId) {
		
		return productConverter.toDTOList(historyRepository.findByUserId(userId));
	}

}
