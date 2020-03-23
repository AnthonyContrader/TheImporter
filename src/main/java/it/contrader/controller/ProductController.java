package it.contrader.controller;

import java.util.List;

import it.contrader.dto.ProductDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.service.ProductService;

public class ProductController implements Controller {
	
	private static String sub_package = "product.";
	
	private ProductService productService;
	
	public ProductController() {
		this.productService = new ProductService();
	}
	
	
	public void doControl(Request request) {
		
        String mode = (String) request.get("mode");
		
		String choice = (String) request.get("choice");
		
		int id;
		String productName;
		int price;
		String brand;
		String desc;
		
		switch(mode){
		
		case "READ":
			id=Integer.parseInt(request.get("id").toString());
			ProductDTO productDTO = productService.read(id);
			request.put("product", productDTO);
			MainDispatcher.getInstance().callView(sub_package+"ProductRead", request);
			break;
			
		case "INSERT":
			productName=request.get("productName").toString();
			price=Integer.parseInt(request.get("price").toString());
			brand=request.get("brand").toString();
			desc=request.get("desc").toString();
			
			ProductDTO productToInsert= new ProductDTO(productName,price);
			
			productService.insert(productToInsert);
			
			request=new Request();
			
			request.put("mode", "mode");
			
			MainDispatcher.getInstance().callView(sub_package+"ProductInsert",request);		
			break;
			
		case "DELETE":
			id=Integer.parseInt(request.get("id").toString());
			
			productService.delete(id);
			request= new Request();
			request.put("mode", "mode");
			MainDispatcher.getInstance().callView(sub_package+"ProductDelete", request);
			break;
			
		case "UPDATE":
			id=Integer.parseInt(request.get("id").toString());
			productName=request.get("productName").toString();
			price=Integer.parseInt(request.get("price").toString());
			desc=request.get("desc").toString();
			brand=request.get("brand").toString();
			ProductDTO productToUpdate=new ProductDTO(id,productName,price);
			productService.update(productToUpdate);
			request=new Request();
			request.put("mode", "mode");
			MainDispatcher.getInstance().callView(sub_package+"ProductUpdate", request);
			break;
			
		case "PRODUCTLIST":
			List<ProductDTO> productsDTO=productService.getAll();
			request.put("products", productsDTO);
			MainDispatcher.getInstance().callView("Product", request);
			break;
		case"GETCHOICE":
			switch(choice.toUpperCase()) {
			case "L":
				MainDispatcher.getInstance().callView(sub_package + "ProductRead", null);
				break;
				
			case "I":
				MainDispatcher.getInstance().callView(sub_package + "ProductInsert", null);
				break;
				
			case "M":
				MainDispatcher.getInstance().callView(sub_package + "ProductUpdate", null);
				break;
				
			case "C":
				MainDispatcher.getInstance().callView(sub_package + "ProductDelete", null);
				break;
				
			case "E":
				MainDispatcher.getInstance().callView("Login", null);
				break;

			case "B":
				MainDispatcher.getInstance().callView("HomeUser", null);
				break;
				
			default:
				MainDispatcher.getInstance().callView("Login", null);
			
			}
			break;
		default:
			MainDispatcher.getInstance().callView("Login", null);
			
		}
	}
	
	
	
	
	
	
	
	
}
