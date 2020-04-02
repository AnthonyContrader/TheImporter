package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.contrader.dto.UserDTO;

import it.contrader.model.User;

@Component
public class UserConverter extends AbstractConverter<User, UserDTO> {

	@Override
	public User toEntity(UserDTO userDTO) {
		User user = null;
		if (userDTO != null) {
			user = new User(userDTO.getId(), userDTO.getUsername(), userDTO.getPassword(), userDTO.getUsertype());
		}
		return user;
	}

	@Override
	public UserDTO toDTO(User user) {
		UserDTO userDTO = null;
		if (user != null) {
			userDTO = new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getUsertype());
		}
		return userDTO;
	}
	
	public List<UserDTO> toDTOList(List<User> userList) {
		//Crea una lista vuota.
		List<UserDTO> userDTOList = new ArrayList<UserDTO>();
		
		//Cicla tutti gli elementi della lista e li converte uno a uno
		for(User user : userList) {
			//Utilizza il metodo toDTO per convertire ogni singolo elemento della lista
			//e lo aggiunge adda lista di DTO
			userDTOList.add(toDTO(user));
		}
		return userDTOList;
	}
}