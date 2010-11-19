package com.scenomania.controllers;

import com.scenomania.sitemap.SitemapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author eugene
 */
@Controller
public class SitemapController {
	
	@Autowired
	private SitemapService sitemapService;
	
	@RequestMapping(method=RequestMethod.GET, value="/smp/")
	public ResponseEntity<String> responseGet() {
		
		sitemapService.process();
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/plain; charset=utf-8");
		
		return new ResponseEntity<String>("done", responseHeaders, HttpStatus.CREATED);
		
	}
}
