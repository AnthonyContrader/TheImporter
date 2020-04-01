package it.contrader.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.History;


@Repository
@Transactional
public interface HistoryRepository extends CrudRepository<History, Integer> {

	List<Integer> findByProductId(int idProduct);
	
	List<Integer> findByUserId(int idProduct);

}
