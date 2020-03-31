package it.contrader.converter;

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
}