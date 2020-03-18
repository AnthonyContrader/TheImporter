package it.contrader.dto;

public class ExcelDTO {
	
	private String directory;
	
	private String title1;
	
	private String title2;
	
	public ExcelDTO() {	}
	
	
	public ExcelDTO(String directory,String title1,String title2) {
		super();
		this.directory = directory;
		this.title1=title1;
		this.title2=title2;
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
}
