package com.scenomania.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scenomania.entities.FeedbackMail;
import com.scenomania.services.FeedbackService;

@Controller
public class FeedbackController extends ControllerBase {
	
	@Autowired(required=true)
	private FeedbackService feedbackService;
	
	@RequestMapping(value="/feedback", method=RequestMethod.GET)
	public String index(Model model){
		model.addAttribute("feedbackMail", new FeedbackMail());
		return "feedback";
	}
	
	@RequestMapping(value="/feedback", method=RequestMethod.POST)
	public String index(@ModelAttribute("feedbackMail") @Valid FeedbackMail feedbackMail,BindingResult result, Model model){
		
		if (result.hasErrors()){
			model.addAttribute("mailErrors", hashErrors(result.getFieldErrors()));
			model.addAttribute("feedbackMail", feedbackMail);
		} else {
			feedbackService.send(feedbackMail.getEmail(), feedbackMail.getText());
			model.addAttribute("success", "true");
		}
		return "feedback";
	}
}
