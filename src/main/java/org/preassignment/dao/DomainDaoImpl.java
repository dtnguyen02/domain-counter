package org.preassignment.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.preassignment.model.Domain;
import org.preassignment.model.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DomainDaoImpl implements DomainDao providing implementation
 * for retrieving and persisting domain related records.
 *
 */
@Repository
public class DomainDaoImpl implements DomainDao {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	/**
	 * @param namedParameterJdbcTemplate
	 */
	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	/**
	 * Map domain columns to Domain object fields.
	 *
	 */
	private static final class DomainMapper implements RowMapper<Domain> {

		public Domain mapRow(ResultSet rs, int rowNum) throws SQLException {
			Domain domain = new Domain();
			domain.setId(rs.getInt("domainid"));
			domain.setName(rs.getString("name"));
			System.out.println("name: " + domain.getName());
			System.out.println("url: " + rs.getString("url"));
			Url url = new Url();
			url.setId(rs.getInt("urlid"));
			url.setUrl(rs.getString("url"));
			domain.addUrl(url);
			return domain;
		}
	}
	
	/**
	 * Map domain columns to Domain object fields.
	 *
	 */
	private static final class DomainCountMapper implements RowMapper<Domain> {

		public Domain mapRow(ResultSet rs, int rowNum) throws SQLException {
			Domain domain = new Domain();
			domain.setId(rs.getInt("domain_id"));
			domain.setName(rs.getString("name"));			
			domain.setCount(rs.getInt("domain_count"));
			return domain;
		}
	}

	/**
	 * Get a domain record, including its count.
	 * 
	 * @param domain The domain name String object to look up.
	 * @param url The url String object to look up.
	 * @return The Domain object with id, name and count set.
	 */
	public Domain getDomain(String domain, String url) {
				
		String sql = "Select Domain.id as domainid, domain.name, url.id as urlid, url.url from domain "
				+ "inner join url on domain.id=url.domain_id where domain.name=:name";
		
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", domain);
        
		List<Domain> domains = namedParameterJdbcTemplate.query(sql, params, new DomainMapper());
		
		Domain resultDomain = new Domain();
		if(domains.size()>0)
			resultDomain.setId(domains.get(0).getId());
		resultDomain.setName(domain);
		resultDomain.setCount(domains.size());		
		
		return resultDomain;
	}

	/**
	 * Get the top domains counted. 
	 * @param numberOfDomains The number of top domains to return.
	 * @return A list containing the top counted Domain objects. 
	 */
	public List<Domain> getTopDomains(int numberOfDomains) {
		String sql = 
			"Select count(domain_id) as domain_count,name, domain_id from url u "
			+ "join domain d on d.id = u.domain_id group by domain_id, name "
			+ "order by domain_count desc limit "
			+ numberOfDomains;
		
		List<Domain> domains = namedParameterJdbcTemplate.query(sql, new DomainCountMapper());
        
		return domains;
	}

	/**
	 * Count the domain. Persist domain and url info.
	 * @param domain The domain String object to count.
	 * @param url The url String object to persist.
	 */
	@Transactional
	public void countDomain(String domainName, String referer) {
				
		int domainId = -1;
		
		//persist the DOMAIN object
		Map<String, Object> insertDomainSqlParams = new HashMap<String, Object>();
		insertDomainSqlParams.put("name", domainName);
        
        String insertDomainSql = "insert into domain (name) values (:name)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try 
        {
			namedParameterJdbcTemplate.update(
					insertDomainSql, 
					new MapSqlParameterSource(insertDomainSqlParams), 
					keyHolder);
		
			domainId = keyHolder.getKey().intValue();
			
		} catch (DataAccessException e) {
			//most likely a unique constraint exception
			//get id from existing domain entry
			System.out.println("There was a problem inserting the domain name.");
		}
        
        //if domainId has not been set yet,
        //get the id from an existing domain record
        if(domainId<0)
        {
        	Domain domain = getDomain(domainName, referer);
			domainId = domain.getId();
        }
        
        //persist the URL object
		String insertUrlSql = 
			"Insert into url (url, domain_id) values (:url, :domainid)";
		
		Map<String, Object> insertUrlSqlParams = new HashMap<String, Object>();
        insertUrlSqlParams.put("url", referer);
        insertUrlSqlParams.put("domainid", domainId);
        KeyHolder urlKeyHolder = new GeneratedKeyHolder();
       
		namedParameterJdbcTemplate.update(
				insertUrlSql, 
				new MapSqlParameterSource(insertUrlSqlParams), 
				urlKeyHolder); 
		
	}

}