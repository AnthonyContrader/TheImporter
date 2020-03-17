package it.contrader.model;

public class Product {
	private int id;
	private String productName;
	private int price;
	
	public Product() {}
	
	public Product(int id, String nomeProdotto, int costo) {
		super();
		this.id = id;
		this.productName = nomeProdotto;
		this.price = costo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getproductName() {
		return productName;
	}

	public void setproductName(String nomeProdotto) {
		this.productName = nomeProdotto;
	}

	public int getprice() {
		return price;
	}

	public void setprice(int costo) {
		this.price = costo;
	}

	@Override
	public String toString() {
		return "product [id=" + id + ", productName=" + productName + ", price=" + price + "]";
	}
	
	

}
