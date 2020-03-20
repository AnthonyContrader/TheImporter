package it.contrader.model;

public class History {
	private int idRecord;
	private int idProduct;
	private int idUser;
	
	public History() {
		
	}
	
	public History(int idRecord, int idProduct, int idUser) {
		super();
		this.setIdProduct(idProduct);
		this.setIdUser(idUser);
		this.setIdRecord(idRecord);
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

	public int getIdRecord() {
		return idRecord;
	}

	public void setIdRecord(int id) {
		this.idRecord = id;
	}
	
	
}
