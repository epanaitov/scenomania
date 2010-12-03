/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.scenomania.controllers;

import com.scenomania.beans.SeoMetaBean;
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
	
	private String bandAction(String bandSlug, Model model, SeoMetaBean meta) {
		return "band";
	}

    @RequestMapping(value="/")
    public String indexAction(Model model, SeoMetaBean meta) {
		
		//model.addAttribute("meta", this.messageSource.getMessage("meta.index", args, locale))
		//meta.setTitle("xyuxyu!!!");
		
		String host = request.getServerName();
		host = "consume-me-after-breakfast.tourageo.us";
		
		Pattern p = Pattern.compile("([a-z\\-]+)\\.[a-z]+\\.[a-z]+");
		Matcher m = p.matcher(StringUtils.lowerCase(host));
		if (m.find()) return bandAction(m.group(1), model, meta);
		
        return "index";
    }

}