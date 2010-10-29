/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.scenomania.controllers;

import com.scenomania.beans.SeoMetaBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;

@Controller
public class IndexController extends ControllerBase {

    @RequestMapping(value="/")
    public String indexAction(Model model, SeoMetaBean meta) {
		
		//model.addAttribute("meta", this.messageSource.getMessage("meta.index", args, locale))
		//meta.setTitle("xyuxyu!!!");
		
        return "index";
    }

}