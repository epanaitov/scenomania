package com.scenomania.entities;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.support.RequestContextUtils;

@MappedSuperclass
public abstract class EntityBase extends Object {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	public void setId(Integer id) {
		this.id = id;
	}

    public Integer getId() {
        return id;
    }


	/**
	 * what's the purpose of that routine?
	 * @param dataHash
	 */
	public void setFromHash(Map dataHash) {
		Iterator itr = dataHash.keySet().iterator();

		Class clazz = this.getClass();

		while (itr.hasNext()) {
			String key = itr.next().toString();

			try {

				Field field = clazz.getDeclaredField(key);
				field.setAccessible(true);
				field.set(this, dataHash.get(key));

			} catch (Exception e) {
				//System.out.println(e);
			}

			

		}
		
	}
	
	public String getUrl() throws Exception {
		return null;
	}
	
	public <T extends EntityLocale> T getLocale(HttpServletRequest request) {
		
		if (this instanceof Localized) {
		
			Locale locale = RequestContextUtils.getLocale(request);
			
			Iterator it = ((Localized) this).getLocales().iterator();

			while (it.hasNext()) {
				T l = (T) it.next();
				if (l.getLocale().equals(locale.getLanguage())) {
					return l;
				}
			}
		
		}
		
		return null;
	}
	
	public String getName() { 
		return "override me";
	}

}
