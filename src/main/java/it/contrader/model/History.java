package it.contrader.model;

public class History {
	private int idProduct;
	private int idUser;
	
	public History() {
		
	}
	
	public History(int idProduct, int idUser) {
		super();
		this.setIdProduct(idProduct);
		this.setIdUser(idUser);
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	
}
