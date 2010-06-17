package com.scenomania.beans;

import java.util.Map;
import javax.faces.context.FacesContext;

import com.scenomania.utils.DummyMap;

public class URLBeanMap extends DummyMap implements Map {

	@Override
	public Object get(Object obj) {
		String uri = (String)obj;
		if (uri == null){
			return "";
		}
		if (uri.startsWith("/")){
			return uri;
		}
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		StringBuilder result = new StringBuilder(path);
		if (! path.endsWith("/")){
			result.append("/");
		}
		result.append(uri);
		return (Object) result.toString();
	}

}
