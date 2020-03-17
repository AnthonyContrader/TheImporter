package it.contrader.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import it.contrader.main.ConnectionSingleton;
import it.contrader.model.Product;


/**
 * 
 * @author Vittorio
 *
 *Per i dettagli della classe vedi Guida sez 6: DAO
 */
public class ProductDAO {

	private final String QUERY_ALL = "SELECT * FROM excel";
	private final String QUERY_CREATE = "INSERT INTO excel (productName, price) VALUES (?,?)";
	private final String QUERY_READ = "SELECT * FROM excel WHERE id=?";
	private final String QUERY_UPDATE = "UPDATE excel SET id=?, productName=?, price=? WHERE id=?";
	private final String QUERY_DELETE = "DELETE FROM excel WHERE id=?";

	public ProductDAO() {
		
	}

	public List<Product> getAll() {
		List<Product> productList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			Product product;
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String productName = resultSet.getString("productName");
				int price = resultSet.getInt("price");
				
				product = new Product(id, productName, price);
				product.setId(id);
				productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productList;
	}

	public boolean insert(Product productToInsert) {
		Connection connection = ConnectionSingleton.getInstance();
		try {	
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			preparedStatement.setString(1, productToInsert.getproductName());
			preparedStatement.setInt(2, productToInsert.getprice());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}

	}
	
	public Product read(int productID) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
			preparedStatement.setInt(1, productID);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String productName;
			int price;

			productName = resultSet.getString("productName");
			price = resultSet.getInt("price");
			
			Product product = new Product(productID, productName, price);
			product.setId(resultSet.getInt("id"));

			return product;
		} catch (SQLException e) {
			return null;
		}

	}
	
}
