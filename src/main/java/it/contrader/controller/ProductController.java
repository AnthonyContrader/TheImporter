package it.contrader.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.dto.ProductDTO;
import it.contrader.dto.UserDTO;
import it.contrader.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping("/getall")
	public String getAll(HttpServletRequest request) {
		setAll(request);
		return "products";
	}

	@GetMapping("/delete")
	public String delete(HttpServletRequest request, @RequestParam("id") Long id) {
		System.out.println("sono nel controller:"+id);
		service.deleteWRecord(id);
		setAll(request);
		return "products";
	}

	@GetMapping("/preupdate")
	public String preUpdate(HttpServletRequest request, @RequestParam("id") Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "updateproduct";
	}

	@PostMapping("/update")
	public String update(HttpServletRequest request, @RequestParam("id") Long id, @RequestParam("productName") String productName,
			@RequestParam("price") int price, @RequestParam("brand") String brand, @RequestParam("description") String description ) {

		ProductDTO dto = new ProductDTO();
		dto.setId(id);
		dto.setProductName(productName);
		dto.setPrice(price);
		dto.setBrand(brand);
		dto.setDescription(description);
		service.update(dto);
		setAll(request);
		return "products";

	}

	@PostMapping("/insert")
	public String insert(HttpServletRequest request, @RequestParam("productName") String productName,
			@RequestParam("price") int price, @RequestParam("brand") String brand, @RequestParam("description") String description) {
		
		UserDTO userDTO = (UserDTO)request.getSession().getAttribute("user"); //raccolgo l'utente loggato
		
		ProductDTO dto = new ProductDTO();
		dto.setProductName(productName);
		dto.setPrice(price);
		dto.setBrand(brand);
		dto.setDescription(description);
		try {
			service.insertWRecord(userDTO, dto);
		} catch (Exception e) { //ricevi questa exception se l'utente non risulta loggato
			request.getSession().setAttribute("userNotLogged", "true"); 
			return "products"; //lo rimando al login
		}
		setAll(request);
		return "products";
	}

	@GetMapping("/read")
	public String read(HttpServletRequest request, @RequestParam("id") Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "readproduct";
	}
	
	@GetMapping("/pagination")
	public String pagination(HttpServletRequest request, @RequestParam("page") int page) {
		request.getSession().setAttribute("page", page);
		setAll(request);
		return "products";
	}

	

	private void setAll(HttpServletRequest request) {
		
		request.getSession().setAttribute("list", service.getAll());
	}
}
