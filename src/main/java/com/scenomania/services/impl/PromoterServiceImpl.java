package com.scenomania.services.impl;

import com.scenomania.dao.PromoterDao;
import com.scenomania.entities.City;
import com.scenomania.entities.Promoter;
import com.scenomania.services.PromoterService;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 *
 * @author eugene
 */
@Service
public class PromoterServiceImpl implements PromoterService {

	@Autowired(required=true)
	private HttpServletRequest request;

	@Autowired(required=true)
	private PromoterDao promoterDao;

	public Promoter find(String name, City city) {
		
		Locale locale = RequestContextUtils.getLocale(request);
		return promoterDao.find(name, locale, city);
	}

	@Transactional
	public Promoter save(Promoter promoter) {
		return this.promoterDao.save(promoter);
	}
}
