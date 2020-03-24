package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

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
		
		public List<HistoryDTO> toDTOList(List<History> historyList) {
			//Crea una lista vuota.
			List<HistoryDTO> historyDTOList = new ArrayList<HistoryDTO>();
			
			//Cicla tutti gli elementi della lista e li converte uno a uno
			for(History record : historyList) {
				//Utilizza il metodo toDTO per convertire ogni singolo elemento della lista
				//e lo aggiunge adda lista di DTO
				historyDTOList.add(toDTO(record));
			}
			return historyDTOList;
		}
}
