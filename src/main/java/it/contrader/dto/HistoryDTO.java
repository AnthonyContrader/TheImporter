package it.contrader.dto;

public class HistoryDTO {
	private int id;
	private int idProduct;
	private int idUser;
	
	public HistoryDTO() {
		super();
	}

	public HistoryDTO(int idProduct, int idUser,int id) {
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
