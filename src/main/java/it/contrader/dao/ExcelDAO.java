package it.contrader.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import it.contrader.utils.ConnectionSingleton;
import it.contrader.model.Excel;
import it.contrader.model.Product;


public class ExcelDAO implements DAO<Excel> {
	
	private final String QUERY_ALL = "SELECT * FROM excel";
	//private final String QUERY_CREATE = "INSERT INTO excel (productName, price, description, brand) VALUES (?,?,?,?)";
	//private final String QUERY_READ = "SELECT * FROM excel WHERE id=?";
	//private final String QUERY_UPDATE = "UPDATE excel SET productName=?, price=? WHERE id=?";
	//private final String QUERY_DELETE = "DELETE FROM excel WHERE id=?";

	public ExcelDAO() {
		
	}

	public List<Product> getAll1() {
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

	public List<Integer> insert(List<Product> productListToInsert) throws SQLException {
		ProductDAO productDAO = new ProductDAO();
		List<Integer> IDproducts = new ArrayList<Integer>();
		for(Product p: productListToInsert){
			IDproducts.add(productDAO.insert2(p));
		}
		return IDproducts;

	}

	@Override
	public List<Excel> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Excel read(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(Excel dto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Excel dto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}


}
