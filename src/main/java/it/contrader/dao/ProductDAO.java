package it.contrader.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.contrader.utils.ConnectionSingleton;
import it.contrader.model.Product;

public class ProductDAO implements DAO<Product> {
	
	private final String QUERY_ALL = "SELECT * FROM excel";
	private final String QUERY_CREATE = "INSERT INTO excel (productName, price, brand, description) VALUES (?,?,?,?)";
	private final String QUERY_READ = "SELECT * FROM excel WHERE id=?";
	private final String QUERY_UPDATE = "UPDATE excel SET productName=?, price=?, description=?, brand=? WHERE id=?";
	private final String QUERY_DELETE = "DELETE FROM excel WHERE id=?";
	
	@Override
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
				String brand= resultSet.getString("brand");
				String desc= resultSet.getString("description");
				
				product = new Product(id, productName, price, desc, brand);
				product.setId(id);
				productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productList;
	}
	
	@Override
	public Product read(int id) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();

			String productName = resultSet.getString("productName");
			int price = resultSet.getInt("price");
			String brand= resultSet.getString("brand");
			String desc= resultSet.getString("description");
			
			Product product = new Product(id, productName, price, desc, brand);
			product.setId(resultSet.getInt("id"));

			return product;
		} catch (SQLException e) {
			return null;
		}
	}
	
	@Override
	public boolean insert(Product productToInsert) {
		return false;
	}
	
	
	public int insert2(Product productToInsert) {
		Connection connection = ConnectionSingleton.getInstance();
		try {	
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			preparedStatement.setString(1, productToInsert.getproductName());
			preparedStatement.setInt(2, productToInsert.getprice());
			preparedStatement.setString(3, productToInsert.getProductBrand());
			preparedStatement.setString(4, productToInsert.getDescription());
			preparedStatement.execute();
			
			//cerco di recupare l'id inserito
			preparedStatement = connection.prepareStatement("SELECT MAX(id) FROM excel");
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			return resultSet.getInt("MAX(id)");
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	@Override
	public boolean update(Product productToUpdate) {
		Connection connection = ConnectionSingleton.getInstance();

		// Check if id is present
		if (productToUpdate.getId() == 0)
			return false;

		Product productRead = read(productToUpdate.getId());
		if (!productRead.equals(productToUpdate)) {
			try {
				// Fill the userToUpdate object
				if (productToUpdate.getproductName() == null || productToUpdate.getproductName().equals("")) {
					productToUpdate.setproductName(productRead.getproductName());
				}

				if (productToUpdate.getprice() == 0) {
					productToUpdate.setprice(productRead.getprice());
				}
				if (productToUpdate.getDescription() == null) {
					productToUpdate.setDescription(productRead.getDescription());
				}
				if (productToUpdate.getProductBrand() == null) {
					productToUpdate.setProductBrand(productRead.getProductBrand());
				}

				// Update the user
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE); 
				preparedStatement.setString(1, productToUpdate.getproductName());
				preparedStatement.setInt(2, productToUpdate.getprice());
				preparedStatement.setString(3, productToUpdate.getDescription());
				preparedStatement.setString(4, productToUpdate.getProductBrand());
				preparedStatement.setInt(5, productToUpdate.getId());
				int a =  preparedStatement.executeUpdate();
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
	@Override
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
