package org.preassignment.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.preassignment.model.Domain;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class DomainDaoTest {

    private EmbeddedDatabase db;

    private NamedParameterJdbcTemplate template;
	private DomainDao domainDao;
    
    @Before
    public void setUp() {
    	db = new EmbeddedDatabaseBuilder()
    		.setType(EmbeddedDatabaseType.HSQL)
    		.addScript("db/sql/create-db.sql")
    		.addScript("db/sql/insert-data.sql")
    		.build();
    	
    	template = new NamedParameterJdbcTemplate(db);
    	domainDao = new DomainDaoImpl();
    	((DomainDaoImpl) domainDao).setNamedParameterJdbcTemplate(template);
    }

    @Test
    public void testGetTopDomains() {    	
    	
    	List<Domain> domains = domainDao.getTopDomains(3);
  
    	Assert.assertNotNull(domains);
    	Assert.assertTrue(domains.size()>0 && domains.size()<4);
    }
    
    @Test
    public void testGetDomain() {
    	String testDomainName = "google.com";
		Domain testDomain = domainDao.getDomain(testDomainName, "http://www.google.com/search?q=test");
    	Assert.assertEquals(2, testDomain.getId().intValue());
    	Assert.assertEquals(testDomainName, testDomain.getName());
    }

    @After
    public void tearDown() {
        db.shutdown();
    }

}