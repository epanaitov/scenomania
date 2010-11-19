package com.scenomania.sitemap;

/**
 *
 * @author eugene
 */
public class Sitemap {
	private String loc = "";

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}
	
	public Sitemap() {
	}
	
	public Sitemap(String location) {
		this.loc = location;
	}
}
