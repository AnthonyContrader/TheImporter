package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

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
			history = new History(historyDTO.getId(), historyDTO.getUser(), historyDTO.getProduct());
		}
		return history;
	}

	@Override
	public HistoryDTO toDTO(History history) {
		HistoryDTO historyDTO = null;
		if (history != null) {
			historyDTO = new HistoryDTO(history.getId(), history.getProduct(), history.getUser());
		}
		return historyDTO;
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