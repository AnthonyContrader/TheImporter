package it.contrader.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import it.contrader.main.ConnectionSingleton;
import it.contrader.model.Product;


public class ExcelDAO {
	
	private final String QUERY_ALL = "SELECT * FROM excel";
	private final String QUERY_CREATE = "INSERT INTO excel (productName, price, description, brand) VALUES (?,?)";
	//private final String QUERY_READ = "SELECT * FROM excel WHERE id=?";
	//private final String QUERY_UPDATE = "UPDATE excel SET productName=?, price=? WHERE id=?";
	//private final String QUERY_DELETE = "DELETE FROM excel WHERE id=?";

	public ExcelDAO() {
		
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
				String description = resultSet.getString("description");
				String brand = resultSet.getString("brand");
				product = new Product(id, productName, price, description, brand);
				product.setId(id);
				productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productList;
	}

	public boolean insert(List<Product> productListToInsert) {
		Connection connection = ConnectionSingleton.getInstance();
		try {	
			for(Product p: productListToInsert){
				PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
				preparedStatement.setString(1, p.getproductName());
				preparedStatement.setInt(2, p.getprice());
				preparedStatement.setString(3, p.getDescription());
				preparedStatement.setString(2, p.getProductBrand());
				preparedStatement.execute();
			}
			return true;
		} catch (SQLException e) {
			return false;
		}

	}


}
