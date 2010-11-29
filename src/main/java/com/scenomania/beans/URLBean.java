package com.scenomania.beans;

import com.scenomania.entities.Band;
import com.scenomania.utils.UrlHelper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component("url")
@Scope("request")
public class URLBean {
	private URLBeanMap get = new URLBeanMap();
	
	
	public URLBeanMap getGet() {
		return get;
	}
	public String getName(){
		return "I am URLBean";
	}
	
	public String link(Band band) {
		String link = "";
		try {
			link = UrlHelper.getUrl(band);
		} catch (Exception e) {
			
		}
		return String.format("<a href=\"%s\">%s</a>", link, band.getName());
	}
}
