package it.contrader.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.dto.HistoryDTO;
import it.contrader.dto.UserDTO;
import it.contrader.service.HistoryService;
import javassist.expr.NewArray;


@Controller
@RequestMapping("/history")
public class HistoryController {

	@Autowired
	private HistoryService service;


	@GetMapping("/getall")
	public String getAll(HttpServletRequest request) {
		setAll(request);
		return "historymanager";
	}

	@GetMapping("/delete")
	public String delete(HttpServletRequest request, @RequestParam("id") Long id) {
		service.delete(id);
		setAll(request);
		return "historymanager";
	}

	@PostMapping("/insert")	//creato solo per definizione, non dovrebbe essere usato
	public String insert(HttpServletRequest request, @RequestParam("iduser") Long idUser,
			@RequestParam("idprodotto") Long idProdotto) {
		HistoryDTO dto = new HistoryDTO();
		dto.getUserDTO().setId(idUser);
		dto.getProductDTO().setId(idProdotto);
		service.insert(dto);
		setAll(request);
		return "historymanager";
	}

	@PostMapping("/searchbyuser")
	public String searchbyuser(HttpServletRequest request, @RequestParam("iduser") Long id) {
		request.getSession().setAttribute("SEARCHBY", "user");
		request.getSession().setAttribute("productlist", service.findByUserId(id));
		return "historyrecord";
	}

	@PostMapping("/searchbyproduct")
	public String searchbyproduct(HttpServletRequest request, @RequestParam("idproduct") Long id) {
		request.getSession().setAttribute("SEARCHBY", "product");
		request.getSession().setAttribute("userlist", service.findByProductId(id));
		return "historyrecord";
	}

	private void setAll(HttpServletRequest request) {
		List<UserDTO> a=new ArrayList<>();
		request.getSession().setAttribute("historyDTOlist", service.getAll());
		request.getSession().setAttribute("list", a);
	}
}
