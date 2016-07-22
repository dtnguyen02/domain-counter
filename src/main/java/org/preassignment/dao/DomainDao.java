package org.preassignment.dao;

import java.util.List;

import org.preassignment.model.Domain;

/**
 * DomainDao contains calls to persistence layer.
 *
 */
public interface DomainDao {
	
	/**
	 * Get a domain record, including its count.
	 * 
	 * @param domain The domain name String object to look up.
	 * @param url The url String object to look up.
	 * @return The Domain object with id, name and count set.
	 */
	public Domain getDomain(String domain, String url);
	
	/**
	 * Get the top domains counted. 
	 * @param numberOfDomains The number of top domains to return.
	 * @return A list containing the top counted Domain objects. 
	 */
	public List<Domain> getTopDomains(int numberOfDomains);
	
	/**
	 * Count the domain. Persist domain and url info.
	 * @param domain The domain String object to count.
	 * @param url The url String object to persist.
	 */
	public void countDomain(String domain, String url);

}