package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.contrader.dto.ProductDTO;

import it.contrader.model.Product;

@Component
public class ProductConverter extends AbstractConverter<Product, ProductDTO> {

	@Override
	public Product toEntity(ProductDTO productDTO) {
		Product product = null;
		if (productDTO != null) {
			product = new Product(productDTO.getId(), productDTO.getProductName(), productDTO.getPrice(), productDTO.getBrand(), productDTO.getDescription());
		}
		return product;
	}

	@Override
	public ProductDTO toDTO(Product product) {
		ProductDTO productDTO = null;
		if (product != null) {
			productDTO = new ProductDTO(product.getId(), product.getProductName(), product.getPrice(), product.getBrand(), product.getDescription());

		}
		return productDTO;
	}
	
	public List<ProductDTO> toDTOList(List<Product> productList) {
		
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