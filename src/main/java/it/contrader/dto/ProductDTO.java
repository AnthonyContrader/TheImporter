package it.contrader.dto;

public class ProductDTO {
	private int id;
	
	private String productName;
	
	private int price;
	
	public ProductDTO() {
		
	}

	public ProductDTO (String productName, int price, String usertype) {
		
		this.productName = productName;
		
		this.price = price;
	}

	public ProductDTO (int id, String productName, int price, String usertype) {
		this.id = id;
		this.productName = productName;
		this.price = price;
	}
	
	public void setId(int id) {
		this.id=id;
	}
	
	public void setProductName(String name) {
		productName=name;
	}
	
	public void setPrice(int price) {
	this.price=price;
    }
	
	public int getId() {
		return id;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public int getPrice() {
		return price;
	}
	
	public String toString() {
		return id + "\t"  + productName +"\t\t" +   price ;
	}
}
