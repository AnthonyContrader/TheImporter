package it.contrader.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.contrader.dto.ProductDTO;
import it.contrader.dto.UserDTO;
import it.contrader.service.ProductService;
import it.contrader.service.Service;

public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProductServlet() {
	}

	public void updateList(HttpServletRequest request) {
		Service<ProductDTO> service = new ProductService();
		List<ProductDTO> listDTO = service.getAll();
		request.setAttribute("list", listDTO);
	}

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Service<ProductDTO> service = new ProductService();
		String mode = request.getParameter("mode");
		ProductDTO dto;
		int id;
		boolean ans;
		switch (mode.toUpperCase()) {
		case "USERLIST":			
			updateList(request);
			getServletContext().getRequestDispatcher("/user/usermanager.jsp").forward(request, response);
			break;
		case "READ":
			id = Integer.parseInt(request.getParameter("id"));
			dto = service.read(id);
			request.setAttribute("dto", dto);

			if (request.getParameter("update") == null) {
				getServletContext().getRequestDispatcher("/user/readuser.jsp").forward(request, response);

			}

			else
				getServletContext().getRequestDispatcher("/user/updateuser.jsp").forward(request, response);

			break;
			
		case "INSERT":
			String productName = request.getParameter("username").toString();
			int price = Integer.parseInt(request.getParameter("username").toString());
			String brand = request.getParameter("username").toString();
			String desc = request.getParameter("username").toString();
			dto = new ProductDTO(productName, price, brand, desc);
			ProductService services=(ProductService) service;
			services.insert2(dto);
			//mancha history
			request.setAttribute("ans", true);
			updateList(request);
			getServletContext().getRequestDispatcher("/user/usermanager.jsp").forward(request, response);
			break;
			
		case "UPDATE":
			productName = request.getParameter("username").toString();
			price = Integer.parseInt(request.getParameter("username").toString());
			brand = request.getParameter("username").toString();
			desc = request.getParameter("username").toString();
			id = Integer.parseInt(request.getParameter("id"));
			dto = new ProductDTO(id,productName, price, brand, desc);
			ans = service.update(dto);
			updateList(request);
			getServletContext().getRequestDispatcher("/user/usermanager.jsp").forward(request, response);
			break;

		}

	}

}
