package it.contrader.service;


import java.sql.SQLException;
import java.util.List;

import it.contrader.converter.ExcelConverter;
import it.contrader.dao.ExcelDAO;
import it.contrader.dto.ExcelDTO;
import it.contrader.model.Excel;


public class ExcelService extends AbstractService<Excel, ExcelDTO> {
	
	private ExcelDAO ExcelDAO;
	private ExcelConverter ExcelConverter;
	
	//Istanzio DAO  e Converter specifici.
	public ExcelService(){
		this.ExcelDAO = new ExcelDAO();
		this.ExcelConverter = new ExcelConverter();
	}


	public List<Integer> insertExcel(ExcelDTO dto) throws SQLException {
		// Converte un DTO in entit� e lo passa al DAO per l'inserimento
		return ExcelDAO.insert(ExcelConverter.toEntity(dto).getProductsList());
	}

	

}
