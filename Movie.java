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
	 * Returns release date of movie
	 * @return: release date of movie
	 */
	public Date getReleaseDate() {
		return releaseDate;
	}
	
	/**
	 * Returns name of movie
	 * @return: name of movie
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns description of movie
	 * @return: the description of a movie
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Returns receive date of a movie
	 * @return: receive date of a movie
	 */
	public Date getReceiveDate() {
		return receiveDate;
	}
	
	/**
	 * Returns status of movie
	 * @return: status of a movie
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Updates the release date of a movie
	 * @param releaseDate
	 */
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	/**
	 * Updates the name of a movie
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Updates description of a movie
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Updates receive dates of a movie
	 * @param receiveDate
	 */
	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}
	
	/**
	 * Updates status of movie
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
