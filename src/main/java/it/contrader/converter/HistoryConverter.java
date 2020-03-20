package it.contrader.converter;

import it.contrader.dto.HistoryDTO;
import it.contrader.model.History;

public class HistoryConverter {
	
	
		public HistoryDTO toDTO(History history) {
			HistoryDTO historyDTO=new HistoryDTO(history.getIdProduct(),history.getIdUser(),history.getIdRecord());
			return historyDTO;
		}
		
		public History toEntity(HistoryDTO historyDTO) {
			History history=new History(historyDTO.getIdProduct(), historyDTO.getIdUser(),historyDTO.getIdRecord());
			return history;
		}
}
