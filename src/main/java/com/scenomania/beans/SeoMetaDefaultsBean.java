package com.scenomania.beans;

import java.util.ResourceBundle;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author eugene
 */
@Component("SeoMeta")
@Scope("request")
public class SeoMetaDefaultsBean {
	
	@Autowired(required=true)
	private SeoMetaBean meta;
	
	private ResourceBundle getBundle() {
		return ResourceBundle.getBundle("messages.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
	}
	
	public String getTitle() {
		
		String title = "";
		if (StringUtils.isEmpty(meta.getTitle())) {
			title = this.getBundle().getString("seo.meta.title.default");
		} else title = meta.getTitle();
		return title + " - Scenomania.ru";
	}
	
	public String getKeywords() {
		
		String keywords = "";
		if (StringUtils.isEmpty(meta.getKeywords())) {
			keywords = this.getBundle().getString("seo.meta.keywords.default");
		} else keywords = meta.getKeywords();
		return keywords;
	}
	
	public String getDescription() {
		
		String description = "";
		if (StringUtils.isEmpty(meta.getDescription())) {
			description = this.getBundle().getString("seo.meta.description.default");
		} else description = meta.getDescription();
		return description;
	}
	
	public String getH1() {
		
		String h1 = "";
		if (StringUtils.isEmpty(meta.getH1())) {
			h1 = this.getBundle().getString("seo.meta.h1.default");
		} else h1 = meta.getH1();
		return h1;
	}
	
}
