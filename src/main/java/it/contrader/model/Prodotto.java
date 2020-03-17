package it.contrader.model;

public class Prodotto {
	private int id;
	private String nomeProdotto;
	private int costo;
	
	public Prodotto() {}
	
	public Prodotto(int id, String nomeProdotto, int costo) {
		super();
		this.id = id;
		this.nomeProdotto = nomeProdotto;
		this.costo = costo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeProdotto() {
		return nomeProdotto;
	}

	public void setNomeProdotto(String nomeProdotto) {
		this.nomeProdotto = nomeProdotto;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	@Override
	public String toString() {
		return "Prodotto [id=" + id + ", nomeProdotto=" + nomeProdotto + ", costo=" + costo + "]";
	}
	
	

}
