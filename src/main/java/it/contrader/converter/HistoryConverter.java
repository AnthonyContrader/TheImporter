package it.contrader.converter;

import org.springframework.stereotype.Component;

import it.contrader.dto.HistoryDTO;
import it.contrader.model.History;

@Component
public class HistoryConverter extends AbstractConverter<History, HistoryDTO> {

	@Override
	public History toEntity(HistoryDTO historyDTO) {
		History history = null;
		if (historyDTO != null) {
			history = new History(historyDTO.getIdRecord(), historyDTO.getIdUser(),historyDTO.getIdProduct());
		}
		return history;
	}

	@Override
	public HistoryDTO toDTO(History history) {
		HistoryDTO historyDTO = null;
		if (history != null) {
			historyDTO = new HistoryDTO(history.getIdRecord(), history.getIdProduct(), history.getIdUser());

		}
		return historyDTO;
	}
}