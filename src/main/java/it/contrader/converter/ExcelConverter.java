package it.contrader.converter;

import it.contrader.dto.ExcelDTO;
import it.contrader.model.Excel;

public class ExcelConverter {

		public ExcelDTO toDTO(Excel excel) {
			ExcelDTO excelDTO=new ExcelDTO(excel.getDirectory());
			return excelDTO;
		}
		
		public Excel toModel(ExcelDTO excelDTO) {
			Excel excel=new Excel(excelDTO.getDirectory());
			return excel;
		}
}
