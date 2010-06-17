package com.scenomania.jsf;

import org.springframework.web.servlet.view.UrlBasedViewResolver;

public class SimpleUrlBasedViewResolver extends UrlBasedViewResolver {
	private String extension = "";
	
	public SimpleUrlBasedViewResolver() {
		super();
	}
	
	@Override
	public String getPrefix(){
		return super.getPrefix();
	}
	@Override
	public String getSuffix(){
		return super.getSuffix();
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
	
}
