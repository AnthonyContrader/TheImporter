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
	//private final String QUERY_DELETE = "DELETE FROM user WHERE id=?";

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

	public List<Product> read(int userId) {
		
		List<Product> productHistory = new ArrayList<Product>();
		Connection connection = ConnectionSingleton.getInstance();
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SEARCHBYUSER);
			preparedStatement.setInt(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()) {
				
				int idRecord,idProduct, idUser;
	
				idProduct = resultSet.getInt("idProduct");
				idUser = resultSet.getInt("idUser");
				idRecord = resultSet.getInt("idRecord");
				History Record = new History(idRecord, idProduct, idUser);
			}
			return user;
		} catch (SQLException e) {
			return null;
		}

	}

	public boolean update(User userToUpdate) {
		Connection connection = ConnectionSingleton.getInstance();

		// Check if id is present
		if (userToUpdate.getId() == 0)
			return false;

		User userRead = read(userToUpdate.getId());
		if (!userRead.equals(userToUpdate)) {
			try {
				// Fill the userToUpdate object
				if (userToUpdate.getUsername() == null || userToUpdate.getUsername().equals("")) {
					userToUpdate.setUsername(userRead.getUsername());
				}

				if (userToUpdate.getPassword() == null || userToUpdate.getPassword().equals("")) {
					userToUpdate.setPassword(userRead.getPassword());
				}

				if (userToUpdate.getUsertype() == null || userToUpdate.getUsertype().equals("")) {
					userToUpdate.setUsertype(userRead.getUsertype());
				}

				// Update the user
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
				preparedStatement.setString(1, userToUpdate.getUsername());
				preparedStatement.setString(2, userToUpdate.getPassword());
				preparedStatement.setString(3, userToUpdate.getUsertype());
				preparedStatement.setInt(4, userToUpdate.getId());
				int a = preparedStatement.executeUpdate();
				if (a > 0)
					return true;
				else
					return false;

			} catch (SQLException e) {
				return false;
			}
		}

		return false;

	}

	public boolean delete(int id) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
			preparedStatement.setInt(1, id);
			int n = preparedStatement.executeUpdate();
			if (n != 0)
				return true;

		} catch (SQLException e) {
		}
		return false;
	}


}
