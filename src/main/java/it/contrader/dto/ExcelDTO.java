package it.contrader.dto;


import java.util.ArrayList;
import java.util.List;

import it.contrader.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ExcelDTO {

	private String directory;
	private String par1, par2, par3, par4;
	
	private List<String> titleRead = new ArrayList<String>();
	private List<Product> productsList = new ArrayList<Product>();

}
