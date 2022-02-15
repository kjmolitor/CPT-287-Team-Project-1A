package project_1a;

import java.util.Date;

public class Movie {
	// Data fields
	private Date releaseDate;
	private String name;
	private String description;
	private Date receiveDate;
	private String status;
	
	// Constructors
	public Movie() {}
	
	public Movie(String name , Date releaseDate, String description, Date receiveDate, String status) {
		this.releaseDate = releaseDate;
		this.name = name;
		this.description = description;
		this.receiveDate = receiveDate;
		this.status = status;
	}
	
	/**
	 * 
	 * @return
	 */
	public Date getReleaseDate() {
		return releaseDate;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * 
	 * @return
	 */
	public Date getReceiveDate() {
		return receiveDate;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * 
	 * @param releaseDate
	 */
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * 
	 * @param receiveDate
	 */
	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}
	
	/**
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
