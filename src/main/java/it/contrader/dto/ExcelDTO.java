package it.contrader.dto;

public class ExcelDTO {
	
	private String directory;
	
	public ExcelDTO() {	}
	
	
	public ExcelDTO(String directory) {
		super();
		this.directory = directory;
	}
	
	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}
}
