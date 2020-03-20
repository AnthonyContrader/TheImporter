package it.contrader.model;

public class History {
	private int id;
	private int idProduct;
	private int idUser;
	
	public History() {
		
	}
	
	public History(int idProduct, int idUser,int id) {
		super();
		this.setIdProduct(idProduct);
		this.setIdUser(idUser);
		this.setId(id);
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
