package project_1a;

import java.util.Date;

public class Movie {
	private Date releaseDate;
	private String name;
	private String description;
	private Date receiveDate;
	private String status;
	
	
	public Movie() {}
	
	public Movie(String name, Date releaseDate, String description, Date receiveDate, String status) {
		this.releaseDate = releaseDate;
		this.name = name;
		this.description = description;
		this.receiveDate = receiveDate;
		this.status = status;
	}
	
	public Date getReleaseDate() {
		return releaseDate;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Date getReceiveDate() {
		return receiveDate;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
}
