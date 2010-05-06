/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.scenomania;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @RequestMapping("/")
    public ModelAndView indexAction() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.addObject("message", "Hello World!");
        return mav;
    }

	@RequestMapping("/xyu")
    public ModelAndView xuyAction() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("xyu");
        mav.addObject("message", "Hello World!");
        return mav;
    }
}