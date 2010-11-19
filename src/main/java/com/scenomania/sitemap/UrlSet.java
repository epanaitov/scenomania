package com.scenomania.sitemap;

import java.util.ArrayList;

/**
 *
 * @author eugene
 */
public class UrlSet {
	
	private ArrayList<Url> urls = new ArrayList<Url>();
	private String xmlns =  "http://www.sitemaps.org/schemas/sitemap/0.9";
	private String xsi = "http://www.w3.org/2001/XMLSchema-instance";
	private String schemaLocation = "http://www.sitemaps.org/schemas/sitemap/0.9 http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd";
	
	public void add(Url url) {
		urls.add(url);
	}

	public String getSchemaLocation() {
		return schemaLocation;
	}

	public ArrayList<Url> getUrls() {
		return urls;
	}

	public String getXmlns() {
		return xmlns;
	}

	public String getXsi() {
		return xsi;
	}
}