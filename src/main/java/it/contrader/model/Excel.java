package it.contrader.model;


import java.util.ArrayList;
import java.util.List;


public class Excel {

	private String directory;
	private String par1, par2; // questi sono gli attributi di prodotto
	
	private List<String> titleRead = new ArrayList<String>();
	private List<Product> productsList = new ArrayList<Product>();

	
	public List<String> getTitleRead() {
		return titleRead;
	}
	
	public List<Product> getProductsList() {
		
		return productsList;
	}
	

	public Excel() {
	}

	public Excel(String directory,String par1,String par2, List<Product> productList) {
		super();
		this.directory = directory;
		this.par1=par1;
		this.par2=par2;
		this.productsList = productList;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}
	
	public void setPar1(String title1) {
		par1 = title1;
	}
	
	public void setPar2(String title2) {
		par2 = title2;
	}
	
	public String getPar1() {
		return par1;
	}
	public String getPar2() {
		return par2;
	}
}
