package com.scenomania.beans;

import java.util.ArrayList;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author eugene
 */

@Component
@Scope("session")
public class SeoPathBean {
	
	private Integer length = 0;
	
	private Object path[] = new Object[10];

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}
	
	public void addCrumb(Object crumb) {
		this.path[this.length] = crumb;
		this.length++;
	}
	
	public Object getCrumb(Integer index) {
		return this.path[index];
	}
	
}
