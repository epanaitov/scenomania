package com.scenomania.utils;
import com.scenomania.entities.Band;
import com.scenomania.entities.City;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author eugene
 */
public class UrlHelper {
	
	public static class InsufficientDataException extends Exception {
	
		InsufficientDataException() {super();}
		
		InsufficientDataException(String message) {super(message);}
	
	}
	
	public static String getSlug(String source) {
		source = StringUtils.trim(source);
		source = StringUtils.lowerCase(source);
		source = source.replaceAll("[^0-9a-z]", "-");
		return source;
	}
	
	public static String getUrl(City city) throws InsufficientDataException {
		if (city.getArea() == null) throw new InsufficientDataException("city's area is null");
		if (city.getArea().getCountry() == null) throw new InsufficientDataException("city's country is null");
		
		return String.format("/%s/%s/%s-%d/", city.getArea().getCountry().getSlug(), city.getArea().getCode(), city.getSlug(), city.getId());
	}
	
	public static String getUrl(Band band) throws InsufficientDataException {
		if (StringUtils.isBlank(band.getName())) throw new InsufficientDataException("band's name is blank");
		String slug = "";
		if (StringUtils.isBlank(band.getSlug())) slug = getSlug(band.getName());
		else slug = band.getSlug();
		return String.format("http://%s.tourageo.us", slug);
	}
}
