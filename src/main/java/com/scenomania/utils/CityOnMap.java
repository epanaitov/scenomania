package com.scenomania.utils;

import com.scenomania.entities.City;
import java.util.Iterator;

/**
 *
 * @author eugene
 */

public class CityOnMap {
	
	private class Band {
		public String url;
		public String name;
	}
	
	public Integer id;
	public Double lat;
	public Double lng;
	public Integer pop;
	public String name;
	public String slug;
	public String countrySlug;
	public String areaCode;
	public Band[] bands = new Band[5];
	
	public CityOnMap(City city) {
		id = city.getId();
		name = city.getName();
		lat = city.getLatitude();
		lng = city.getLongitude();
		countrySlug = city.getArea().getCountry().getSlug();
		areaCode = city.getArea().getCode();
		pop = city.getPopulation();
		slug = city.getSlug();
		
		Iterator<com.scenomania.entities.Band> bit = city.getBands().iterator();
		int b = 0;
		while (bit.hasNext()) {
			com.scenomania.entities.Band band = bit.next();
			try {
				Band comBand = new Band();
				comBand.name = band.getName();
				comBand.url = UrlHelper.getUrl(band);
				bands[b] = comBand;
				b++;
			} catch (Exception e) {
				continue;
			}
			if (b == 5) break;
		}
	}
}
