package it.contrader.dto;

public class HistoryDTO {
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
