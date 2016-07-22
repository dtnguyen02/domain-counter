package org.preassignment.servlet3;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import org.preassignment.config.SpringRootConfig;
import org.preassignment.config.SpringWebConfig;

/**
 * 
 * Class to programmatically initialize web app configuration.
 * 
 * @author Kathy Nguyen
 *
 */
public class MyWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { SpringRootConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { SpringWebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		// use hsql profile (annotated class HsqlDataSource)
		servletContext.setInitParameter("spring.profiles.active", "hsql");
	}

}