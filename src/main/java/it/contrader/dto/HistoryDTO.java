package it.contrader.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class HistoryDTO {

	private Long id;

	private ProductDTO productDTO;
	
	private UserDTO UserDTO;

}
