package org.preassignment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Url model object.
 *
 */
@Entity
@Table(name="URL")
public class Url {

	private Integer id;
	private String url;

	/**
	 * Get the Id of url.
	 * @return
	 */
	@Id
	public Integer getId() {
		return id;
	}

	/**
	 * Set the Id of url.
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Get the url String object.
	 * @return the url String object.
	 */
	@Column(name="url")
	public String getUrl() {
		return url;
	}

	/**
	 * Set the url String object.
	 * @param url the String object to set url to.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Url [id=" + id + ", url=" + url + "]";
	}

}