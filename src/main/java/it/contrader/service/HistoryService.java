package it.contrader.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import it.contrader.converter.HistoryConverter;
import it.contrader.dao.HistoryRepository;
import it.contrader.dto.HistoryDTO;
import it.contrader.dto.ProductDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.History;


@Service
public class HistoryService extends AbstractService<History, HistoryDTO> {

	@Autowired
	private HistoryConverter converter;
	@Autowired
	private HistoryRepository historyRepository;
	private UserService userService;
	private ProductService productService;

	public List<UserDTO> findByProductId(int productId) {
		List<UserDTO> userList = new ArrayList<UserDTO>();
		
		List<Integer> userListId = historyRepository.findByProductId(productId);
		for(Integer i: userListId) {
			userList.add(userService.getById(i.longValue()));
		}
		return userList;
	}
	
	public List<ProductDTO> findByUserId(int userId) {
		
		List<ProductDTO> productList = new ArrayList<ProductDTO>();
		
		List<Integer> productListId = historyRepository.findByUserId(userId);
		for(Integer i: productListId) {
			productList.add(productService.getById(i.longValue()));
		}
		return productList;
	}

}
