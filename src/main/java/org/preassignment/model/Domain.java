package org.preassignment.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Domain model object.
 *
 */
@Entity
@Table(name="Domain", uniqueConstraints = { @UniqueConstraint(columnNames="name")})
public class Domain {

	private Integer id;
	private String name;
	private transient Integer count;
	private List<Url> urls;

	/**
	 * Unique id field of the domain object.
	 * 
	 * @return id Integer object.
	 * 
	 */
	@Id
	public Integer getId() {
		return id;
	}

	/**
	 * Set the unique id field.
	 * 
	 * @param id Integer id object to set id to.
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Number of times this domain has been seen.
	 * 
	 * @return count Integer object.
	 * 
	 */
	public Integer getCount() {
		return count;
	}

	/** 
	 * Set the count.
	 * 
	 * @param count Integer object to set count to.
	 * 
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * Get the domain name String object.
	 * @return the domain name String object.
	 */
	@Column(name = "name")
	public String getName() {
		return name;
	}

	/**
	 * Set the domain name String object.
	 * @param name the String object to set name to.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return
	 */
	@Column
    @ElementCollection(targetClass=Url.class)
	public List<Url> getUrls() {
		return urls;
	}

	/**
	 * @param urls
	 */
	public void setUrls(List<Url> urls) {
		this.urls = urls;
	}

	/**
	 * @param url
	 */
	public void addUrl(Url url) {
		if(urls==null)
		{
			this.urls = new ArrayList<Url>();
		}
			
		this.urls.add(url);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Domain [id=" + id + ", name=" + name + ", urls=" + urls + "]";
	}

}