package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.ExcelDTO;
import it.contrader.dto.ProductDTO;
import it.contrader.model.Excel;
import it.contrader.model.Product;

public class ExcelConverter implements Converter<Excel, ExcelDTO> {

		public ExcelDTO toDTO(Excel excel) {
			ExcelDTO excelDTO=new ExcelDTO(excel.getDirectory(),excel.getPar1(),excel.getPar2(),excel.getPar3(),excel.getPar4(), excel.getProductsList());
			return excelDTO;
		}
		
		public Excel toEntity(ExcelDTO excelDTO) {
			Excel excel=new Excel(excelDTO.getDirectory(),excelDTO.getTitle1(),excelDTO.getTitle2(),excelDTO.getTitle3(),excelDTO.getTitle4(),excelDTO.getProductList());
			return excel;
		}

		@Override
		public List<ExcelDTO> toDTOList(List<Excel> entityList) {
			List<ExcelDTO> result = new ArrayList<ExcelDTO>();
			for(Excel temp: entityList) {
				result.add(toDTO(temp));
			}
			return null;
		}
}
