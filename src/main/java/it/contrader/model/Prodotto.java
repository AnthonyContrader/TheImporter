package it.contrader.model;

public class Prodotto {
	private int id;
	private String productName;
	private int price;
	
	public Prodotto() {}
	
	public Prodotto(int id, String nomeProdotto, int costo) {
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

	public String getNomeProdotto() {
		return productName;
	}

	public void setNomeProdotto(String nomeProdotto) {
		this.productName = nomeProdotto;
	}

	public int getCosto() {
		return price;
	}

	public void setCosto(int costo) {
		this.price = costo;
	}

	@Override
	public String toString() {
		return "Prodotto [id=" + id + ", nomeProdotto=" + productName + ", costo=" + price + "]";
	}
	
	

}
