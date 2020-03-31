package it.contrader.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductDTO {

	private Long id;

	private String productName;
	
	private int price;
	
	private String brand;
	
	private String description;

}
