package it.contrader.service;

import it.contrader.converter.ProductConverter;
import it.contrader.dao.ProductDAO;
import it.contrader.dto.ProductDTO;
import it.contrader.model.Product;

public class ProductService extends AbstractService<Product, ProductDTO> {
		
		public ProductService() {
			this.dao=new ProductDAO();
			this.converter = new ProductConverter();
		}
		
		public int insertProduct(ProductDTO dto ) {
			ProductDAO productDAO=(ProductDAO) dao;
			ProductConverter productConverter=(ProductConverter) converter;
			return productDAO.insert2(productConverter.toEntity(dto));
		}
}
