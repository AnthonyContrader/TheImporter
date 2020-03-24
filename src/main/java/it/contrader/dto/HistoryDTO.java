package it.contrader.dto;

public class HistoryDTO {
	private int idRecord;
	private int idProduct;
	private int idUser;
	
	public HistoryDTO() {
		super();
	}

	public HistoryDTO(int idProduct, int idUser) {
		super();
		this.setIdProduct(idProduct);
		this.setIdUser(idUser);
	}
	
	public HistoryDTO(int idProduct, int idUser,int id) {
		super();
		this.setIdProduct(idProduct);
		this.setIdUser(idUser);
		this.setIdRecord(id);
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
