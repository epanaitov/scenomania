package com.scenomania.utils;

import com.scenomania.entities.EntityLocale;
import com.scenomania.entities.Localized;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 *
 * @author eugene
 */
public class LocaleHelper {
	
	public static <T extends EntityLocale> T getLocale(Localized source, HttpServletRequest request) {
		
		Locale locale = RequestContextUtils.getLocale(request);
		
		Iterator it = source.getLocales().iterator();
		
		while (it.hasNext()) {
			T l = (T) it.next();
			if (l.getLocale().equals(locale.getLanguage())) {
				return l;
			}
		}
		
		return null;
		
	}
}
