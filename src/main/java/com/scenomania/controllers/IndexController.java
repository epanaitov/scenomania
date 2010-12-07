/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.scenomania.controllers;

import com.scenomania.beans.SeoMetaBean;
import com.scenomania.dao.BandDao;
import com.scenomania.entities.Band;
import com.scenomania.utils.UrlHelper;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;

@Controller
public class IndexController extends ControllerBase {
	
	@Autowired
	private ServletRequest request;
	
	@Autowired
	private BandDao bandDao;
	
	private String bandAction(String bandSlug, Model model, SeoMetaBean meta) {
		
		Band found = bandDao.find(bandSlug);
		
		if (found == null) {
			meta.setIndex(Boolean.FALSE);
			meta.setFollow(Boolean.TRUE);
			meta.setH1("Нет такой группы");
			meta.setTitle("Нет такой группы");
			model.addAttribute("error", "Группа "+UrlHelper.unSlug(bandSlug)+" у нас не зарегистрирована.<br /><a href=\"register\">Но это моя группа!</a>");
		}
		
		return "band";
	}

    @RequestMapping(value="/")
    public String indexAction(Model model, SeoMetaBean meta) {
		
		String host = request.getServerName();
		host = "xyu-xyu.tourageo.us";
		
		Pattern p = Pattern.compile("([a-z\\-]+)\\.[a-z]+\\.[a-z]+");
		Matcher m = p.matcher(StringUtils.lowerCase(host));
		if (m.find()) return bandAction(m.group(1), model, meta);
		
        return "index";
    }

}