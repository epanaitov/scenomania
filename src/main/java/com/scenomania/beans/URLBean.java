package com.scenomania.beans;

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
}
