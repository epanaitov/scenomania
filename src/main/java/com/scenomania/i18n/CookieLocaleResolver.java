package com.scenomania.i18n;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

public class CookieLocaleResolver extends
		org.springframework.web.servlet.i18n.CookieLocaleResolver {
	
	//Allowed locales
	protected List<String> locales;
		
	public List<String> getLocales() {
		return locales;
	}

	public void setLocales(List<String> locales) {
		this.locales = locales;
	}
	

	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		Locale locale = super.resolveLocale(request);
		if (this.locales.contains(locale.getLanguage().toLowerCase())){
			return locale;
		} else {
			return super.determineDefaultLocale(request);
		}
	}
}
