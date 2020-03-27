package it.contrader.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.contrader.dto.HistoryDTO;
import it.contrader.dto.ProductDTO;
import it.contrader.dto.UserDTO;
import it.contrader.service.HistoryService;
import it.contrader.service.ProductService;
import it.contrader.service.Service;

public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Service<HistoryDTO> historyService;
	
	public ProductServlet() {
	}

	public void updateList(HttpServletRequest request) {
		Service<ProductDTO> service = new ProductService();
		List<ProductDTO> listDTO = service.getAll();
		request.setAttribute("list", listDTO);
	}

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Service<ProductDTO> service = new ProductService();
		historyService = new HistoryService();
		
		String mode = request.getParameter("mode");
		ProductDTO dto;
		int id;
		boolean ans;
		switch (mode.toUpperCase()) {
		case "PRODUCTLIST":			
			updateList(request);
			getServletContext().getRequestDispatcher("/product/productmanager.jsp").forward(request, response);
			break;
		case "READ":
			id = Integer.parseInt(request.getParameter("id"));
			dto = service.read(id);
			request.setAttribute("dto", dto);

			if (request.getParameter("update") == null) {
				getServletContext().getRequestDispatcher("/product/readproduct.jsp").forward(request, response);

			}

			else
				getServletContext().getRequestDispatcher("/product/updateproduct.jsp").forward(request, response);

			break;
			
		case "INSERT":
			String productName = request.getParameter("productname").toString();
			int price = Integer.parseInt(request.getParameter("price").toString());
			String brand = request.getParameter("brand").toString();
			String desc = request.getParameter("description").toString();
			dto = new ProductDTO(productName, price, brand, desc);
			ProductService services=(ProductService) service;
			int idProduct = services.insertProduct(dto);
			//mancha history
			UserDTO userLogged = (UserDTO) request.getSession().getAttribute("user");
			HistoryDTO record = new HistoryDTO(idProduct,userLogged.getId());
			//modificato
			historyService.insert(record);
			request.setAttribute("ans", true);
			updateList(request);
			getServletContext().getRequestDispatcher("/product/productmanager.jsp").forward(request, response);
			break;
			
		case "UPDATE":
			productName = request.getParameter("productname").toString();
			price = Integer.parseInt(request.getParameter("price").toString());
			brand = request.getParameter("brand").toString();
			desc = request.getParameter("description").toString();
			id = Integer.parseInt(request.getParameter("id"));
			dto = new ProductDTO(id,productName, price, brand, desc);
			ans = service.update(dto);
			updateList(request);
			getServletContext().getRequestDispatcher("/product/productmanager.jsp").forward(request, response);
			break;

		case "DELETE":
			id = Integer.parseInt(request.getParameter("id"));
			ans = service.delete(id);
			if(ans) {
				historyService.deleteByProductId(id);
			}
			else System.out.println("cancellazione mancata di prodotto, id: "+id );
			request.setAttribute("ans", ans);
			updateList(request);
			getServletContext().getRequestDispatcher("/product/productmanager.jsp").forward(request, response);
			break;
		}

	}

}
