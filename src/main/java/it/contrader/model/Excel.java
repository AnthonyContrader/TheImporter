package it.contrader.model;


import java.util.ArrayList;
import java.util.List;


public class Excel {

	private String directory;
	private String par1, par2, par3, par4; // questi sono gli attributi di prodotto
	
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

	public Excel(String directory,String par1, String par2, String par3, String par4, List<Product> productList) {
		super();
		this.directory = directory;
		this.par1=par1;
		this.par2=par2;
		this.par3=par3;
		this.par4=par4;
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

	public String getPar3() {
		return par3;
	}

	public void setPar3(String par3) {
		this.par3 = par3;
	}

	public String getPar4() {
		return par4;
	}

	public void setPar4(String par4) {
		this.par4 = par4;
	}
}
