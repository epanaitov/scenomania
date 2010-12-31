package com.scenomania.beans;

import com.scenomania.entities.EntityBase;
import com.scenomania.entities.EntityLocale;
import com.scenomania.entities.User;
import com.scenomania.services.UserService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
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
	
	@Autowired(required=true)
	private UserService userService;
	
	@Autowired(required=true)
	private SeoPathBean path;
	
	@Autowired(required=true)
	private HttpServletRequest request;
	
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
		User user = userService.getLogged();
		if (user != null) return "Привет, "+user.getNickname();
		String h1 = "";
		if (StringUtils.isEmpty(meta.getH1())) {
			h1 = this.getBundle().getString("seo.meta.h1.default");
		} else h1 = meta.getH1();
		return h1;
	}
	
	public String getRobots() {
		if (meta == null) return "index, follow";
		StringBuilder robots = new StringBuilder();
		if ((meta.getIndex() == null) || meta.getIndex()) robots.append("index");
		else robots.append("noindex");
		if ((meta.getFollow() == null) || meta.getFollow()) robots.append(",").append("follow");
		else robots.append(",").append("nofollow");
		return robots.toString();
	}
	
	public String getPath() {
		if (path == null) return "";
		if (path.getLength() == 0) return "";
		Collection res = new ArrayList();
		for (int i=0; i < path.getLength(); i++) {
			Object item = path.getCrumb(i);
			if (item instanceof EntityBase) {
				EntityBase entity = (EntityBase) item;
				try {
					String url = entity.getUrl();
					
					String crumb = "";
					
					if (url != null) crumb = "<a href=\""+url+"\">";
					
					EntityLocale el = (EntityLocale) entity.getLocale(request);
					
					if (el == null) crumb += entity.getName();
					else crumb += el.getName();
					
					if (url != null) crumb += "</a>";
					res.add(crumb);
				} catch (Exception e) {
				}
				
			}
			if (item instanceof String) {
				res.add(item);
			}
		}
		return StringUtils.join(res, " &gt; ");
	}
}
