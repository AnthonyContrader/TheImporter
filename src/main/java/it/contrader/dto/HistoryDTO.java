package it.contrader.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class HistoryDTO {

	private int idRecord;

	private int idProduct;
	
	private int idUser;

}
