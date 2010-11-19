package com.scenomania.sitemap;

import java.util.ArrayList;

/**
 *
 * @author eugene
 */
public class SitemapIndex {
	private ArrayList<Sitemap> sitemaps = new ArrayList<Sitemap>();

	public ArrayList<Sitemap> getSitemaps() {
		return sitemaps;
	}

	public void setSitemaps(ArrayList<Sitemap> sitemaps) {
		this.sitemaps = sitemaps;
	}
	
	public void add(Sitemap map) {
		this.sitemaps.add(map);
	}
	
}
