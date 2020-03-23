package it.contrader.model;

public class Product {
	private int id;
	private String productName;
	private int price;
	private String productBrand;
	private String description;
	
	
	public Product() {}
	
	public Product(int id, String nomeProdotto, int costo,String brand,String desc) {
		super();
		this.id = id;
		this.productName = nomeProdotto;
		this.price = costo;
		this.productBrand=brand;
		this.description=desc;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + price;
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		if (price != other.price)
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		return true;
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
