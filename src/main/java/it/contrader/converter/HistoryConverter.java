package it.contrader.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.contrader.dto.HistoryDTO;
import it.contrader.model.History;

@Component
public class HistoryConverter extends AbstractConverter<History, HistoryDTO> {

	
	//problemi successivi nel controller
@Autowired	
ProductConverter productConverter;
@Autowired
UserConverter userConverter;
	
	@Override
	public History toEntity(HistoryDTO historyDTO) {
		History history = null;
		if (historyDTO != null) {
			history = new History(historyDTO.getId(), userConverter.toEntity(historyDTO.getUserDTO()), productConverter.toEntity(historyDTO.getProductDTO()));
		}
		return history;
	}

	@Override
	public HistoryDTO toDTO(History history) {
		HistoryDTO historyDTO = null;
		if (history != null) {
			historyDTO = new HistoryDTO(history.getId(), productConverter.toDTO(history.getProduct()), userConverter.toDTO(history.getUser()));
		}
		return historyDTO;
	}
}