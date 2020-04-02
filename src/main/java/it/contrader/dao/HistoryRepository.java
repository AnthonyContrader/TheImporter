package it.contrader.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



import it.contrader.model.History;
import it.contrader.model.Product;
import it.contrader.model.User;


@Repository
@Transactional
public interface HistoryRepository extends CrudRepository<History, Long> {

	History findByProduct(Product product);
	
	History findByUser(User user);

}
