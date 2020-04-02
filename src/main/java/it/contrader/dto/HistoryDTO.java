package it.contrader.dto;


import it.contrader.model.Product;
import it.contrader.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class HistoryDTO {

	private Long id;

	private Product product;
	
	private User user;

}
