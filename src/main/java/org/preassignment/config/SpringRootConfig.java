package org.preassignment.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * SpringRootConfig used for spring configuration.
 * Specify the package for components scan (classes should be annotated).
 *
 */
@ComponentScan({ "org.preassignment" })
@Configuration
public class SpringRootConfig {

	@Autowired
	DataSource dataSource;

	@Bean
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return new NamedParameterJdbcTemplate(dataSource);
	}
	
	/**
	 * 
	 * This method starts up the hsqldb db manager ui tool.
	 * 
	 */
	@PostConstruct
	public void startDBManager() {		
		//hsqldb
		DatabaseManagerSwing.main(new String[] { "--url", "jdbc:hsqldb:mem:testdb", "--user", "sa", "--password", "" });
	}
}