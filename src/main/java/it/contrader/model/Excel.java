package it.contrader.model;


import java.util.ArrayList;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Excel {


	private String directory;
	private String par1, par2, par3, par4;
	
	private List<String> titleRead = new ArrayList<String>();
	private List<Product> productsList = new ArrayList<Product>();

}
