package it.contrader.dto;

import java.util.List;

import it.contrader.model.Product;

public class ExcelDTO {
	
	private String directory;
	
	private String title1, title2, title3, title4;
	
	
	private List<Product> productList;
	
	public List<Product> getProductList() {
		return productList;
	}


	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}


	public ExcelDTO() {	}
	
	
	public ExcelDTO(String directory,String title1,String title2,String title3,String title4, List<Product> productList) {
		super();
		this.directory = directory;
		this.title1=title1;
		this.title2=title2;
		this.setTitle3(title3);
		this.setTitle4(title4);
		this.productList = productList;
	}
	
	public String getDirectory() {
		return directory;
	}
	
	public String getTitle1() {
		return title1;
	}
	
	public String getTitle2() {
		return title2;
	}

	public void setTitle1(String title1) {
		this.title1=title1;
	}
	
	public void setTitle2(String title2) {
		this.title2=title2;
	}
	public void setDirectory(String directory) {
		this.directory = directory;
	}


	public String getTitle3() {
		return title3;
	}


	public void setTitle3(String title3) {
		this.title3 = title3;
	}


	public String getTitle4() {
		return title4;
	}


	public void setTitle4(String title4) {
		this.title4 = title4;
	}

}
