package it.contrader.dto;

public class ProductDTO {
	private int id;
	private String productName;	
	private int price;
	private String productBrand;
	private String description;
	
	public ProductDTO() {
		
	}
	
	public ProductDTO (String productName, int price,String brand,String desc) {
		this.productName = productName;
		this.price = price;
		this.productBrand=brand;
		this.description=desc;
	}

	public ProductDTO (int id, String productName, int price,String brand,String desc) {
		this.id = id;
		this.productName = productName;
		this.price = price;
		this.productBrand=brand;
		this.description=desc;
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
		return id + "\t"  + productName +"\t\t" +   price +"\t\t" +   productBrand+"\t\t" +   description;
	}

	public String getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
