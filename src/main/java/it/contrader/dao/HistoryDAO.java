package it.contrader.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import it.contrader.main.ConnectionSingleton;
import it.contrader.model.History;
import it.contrader.model.Product;
import it.contrader.model.User;

/**
 * 
 * @author Vittorio
 *
 *Per i dettagli della classe vedi Guida sez 6: DAO
 */
public class HistoryDAO {

	private final String QUERY_ALL = "SELECT * FROM history";
	private final String QUERY_CREATE = "INSERT INTO history (idProduct, idUser) VALUES (?,?)";
	private final String QUERY_SEARCHBYUSER = "SELECT * FROM history WHERE idUser=?";
	private final String QUERY_SEARCHBYPRODUCT = "SELECT * FROM history WHERE idProduct=?";
	//private final String QUERY_UPDATE = "UPDATE user SET username=?, password=?, usertype=? WHERE id=?";
	private final String QUERY_DELETEBYPRODUCT = "DELETE FROM history WHERE idProduct=?";
	private final String QUERY_DELETEBYUSER = "DELETE FROM history WHERE idUser=?";
	
	public HistoryDAO() {

	}

	public List<History> getAll() {						//ritorna tutti i record della tabella
		List<History> recordList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			History record;
			while (resultSet.next()) {
				int idRecord = resultSet.getInt("idRecord");
				int idProduct = resultSet.getInt("idProduct");
				int idUser= resultSet.getInt("idUser");
				
				record = new History(idRecord, idProduct, idUser);
				record.setIdRecord(idRecord);
				recordList.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recordList;
	}

	public boolean insert(History recordToInsert) {						//inserisce un record
		Connection connection = ConnectionSingleton.getInstance();
		try {	
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			preparedStatement.setInt(1, recordToInsert.getIdProduct());
			preparedStatement.setInt(2, recordToInsert.getIdUser());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	public boolean insertList(List<History> recordsToInsert) throws SQLException {						//inserisce un record
		
		for(History record: recordsToInsert) {
			insert(record);
		}
		return true;
	}
	
	public List<Product> searchByUserId(int userId) {
		
		ProductDAO productsDAO = new ProductDAO();
		List<Product> productHistory = new ArrayList<Product>();
		Connection connection = ConnectionSingleton.getInstance();
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SEARCHBYUSER);
			preparedStatement.setInt(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()) {
				
				int idProduct;
	
				idProduct = resultSet.getInt("idProduct");
				Product product = productsDAO.read(idProduct);
				productHistory.add(product);
			}
			return productHistory;
		} catch (SQLException e) {
			return null;
		}

	}
	
	public List<User> searchByProductId(int productId) {
		
		UserDAO usersDAO = new UserDAO();
		List<User> userHistory = new ArrayList<User>();
		Connection connection = ConnectionSingleton.getInstance();
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SEARCHBYPRODUCT);
			preparedStatement.setInt(1, productId);
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()) {
				
				int idUser;
	
				idUser = resultSet.getInt("idUser");
				User user = usersDAO.read(idUser);
				userHistory.add(user);
			}
			return userHistory;
		} catch (SQLException e) {
			return null;
		}

	}
	
	public boolean deleteByProductId(Product productTODelete) {
		
		Connection connection = ConnectionSingleton.getInstance();
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETEBYPRODUCT);
			preparedStatement.setInt(1, productTODelete.getId());
			preparedStatement.executeUpdate();
			
				return true;

		} catch (Exception e) {
			return false;
		}

	}	
	
public boolean deleteByUserId(User userToDelete) {
		
		Connection connection = ConnectionSingleton.getInstance();
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETEBYUSER);
			preparedStatement.setInt(1, userToDelete.getId());
			preparedStatement.executeUpdate();
			
				return true;

		} catch (Exception e) {
			return false;
		}

	}	


}
