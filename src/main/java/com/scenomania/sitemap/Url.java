package com.scenomania.sitemap;

/**
 *
 * @author eugene
 */
public class Url {
	
	private String loc = "";
	private String changefreq = "monthly";
	private String priority = "0.4";

	public String getChangefreq() {
		return changefreq;
	}

	public void setChangefreq(String changefreq) {
		this.changefreq = changefreq;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}
}
