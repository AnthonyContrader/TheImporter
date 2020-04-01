package it.contrader.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.History;


@Repository
@Transactional
public interface HistoryRepository extends CrudRepository<History, Integer> {

	int findByProductId(int idProduct);
	
	int findByUserId(int idProduct);

}
