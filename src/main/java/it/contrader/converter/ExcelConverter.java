package it.contrader.converter;

import org.springframework.stereotype.Component;

import it.contrader.dto.ExcelDTO;
import it.contrader.model.Excel;

@Component
public class ExcelConverter extends AbstractConverter<Excel, ExcelDTO> {

	@Override
	public Excel toEntity(ExcelDTO excelDTO) {
		Excel excel = null;
		if (excelDTO != null) {
			excel = new Excel(excelDTO.getDirectory(),excelDTO.getPar1(),excelDTO.getPar2(),excelDTO.getPar3(),excelDTO.getPar4(),excelDTO.getTitleRead(), excelDTO.getProductsList());
		}
		return excel;
	}

	@Override
	public ExcelDTO toDTO(Excel excel) {
		ExcelDTO excelDTO = null;
		if (excel != null) {
			excelDTO = new ExcelDTO(excel.getDirectory(),excel.getPar1(),excel.getPar2(),excel.getPar3(),excel.getPar4(),excel.getTitleRead(), excel.getProductsList());

		}
		return excelDTO;
	}
}