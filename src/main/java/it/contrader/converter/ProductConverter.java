package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;
import it.contrader.dto.ProductDTO;
import it.contrader.model.Product;




public class ProductConverter {

	
	public ProductDTO toDTO(Product product) {
		ProductDTO productDTO =new ProductDTO(product.getId(),product.getproductName(),product.getprice(),product.getProductBrand(),product.getDescription());
		return productDTO;
	}
	
	public Product toEntity(ProductDTO productDTO) {
		Product product =new Product (productDTO.getId(),productDTO.getProductName(),productDTO.getPrice(),productDTO.getProductBrand(),productDTO.getDescription());
		return product;
	}
	
	public List<ProductDTO> toDTOList(List<Product> productList) {
		//Crea una lista vuota.
		List<ProductDTO> productDTOList = new ArrayList<ProductDTO>();
		
		//Cicla tutti gli elementi della lista e li converte uno a uno
		for(Product product : productList) {
			//Utilizza il metodo toDTO per convertire ogni singolo elemento della lista
			//e lo aggiunge adda lista di DTO
			productDTOList.add(toDTO(product));
		}
		return productDTOList;
	}
}
