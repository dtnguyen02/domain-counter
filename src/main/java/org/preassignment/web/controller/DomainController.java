package org.preassignment.web.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.validator.routines.UrlValidator;
import org.preassignment.dao.DomainDao;
import org.preassignment.model.Domain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * DomainController handle requests to the domain-counter services.
 * 
 * @author Kathy Nguyen
 *
 */
@Controller
public class DomainController {

	@Autowired
	DomainDao domainDao;
	
	/**
	 * 
	 * topDomains will get the top domains counted
	 * 
	 * @param numberOfDomains the number of domains to return.
	 * @param model the model object to add the topDomains to.
	 * 
	 * @return path to page that will list the top domains counted.
	 * 
	 */
	@RequestMapping(value = "/topdomains/{numberOfDomains}", method = RequestMethod.GET)
	public String topDomains(
			@PathVariable(value="numberOfDomains") int numberOfDomains, 
			Model model) 
	{
		List<Domain> topDomains = domainDao.getTopDomains(numberOfDomains);

		model.addAttribute("domains", topDomains);
		
		return "topdomains";
	}
	
	/**
	 * 
	 * getDomain will return the number of times this site has seen this domain.
	 * It will also "count" it by saving it in the database.
	 * 
	 * @param urlRequestParam The url request parameter to get the domain from.
	 * @param model The model object to add the domain object to.
	 * 
	 * @return path to page that will display this domain's count.
	 * 
	 * @throws MalformedURLException
	 * 
	 */
	@RequestMapping(value = "/getDomain", method = RequestMethod.GET)
	public String getDomain(
			@RequestParam("url") String urlRequestParam, 
			Model model) throws MalformedURLException 
	{
		//validate that the url request parameter is a valid url
		//if not, then throw an exception
		UrlValidator urlValidator = new UrlValidator();
		if(!urlValidator.isValid(urlRequestParam))
			throw new MalformedURLException();
		
		//url is valid so get the domain part of it
		URL url = new URL(urlRequestParam);
		String domainName = url.getHost();
		
		//count this domain, save it to the domain table if it's not already there
		//add the url to the database
		domainDao.countDomain(domainName, urlRequestParam);
		
		//get the count of this domain
		Domain domain = domainDao.getDomain(domainName, urlRequestParam);

		model.addAttribute("domain", domain);
		
		return "domainCounter";
	}

}