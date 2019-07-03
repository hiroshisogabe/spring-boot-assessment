package br.com.sogabe.assessment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.sogabe.assessment.service.BracketService;

@Controller
public class HomeController {
	
	@Autowired
	private BracketService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "home";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView index(String brackets) {
		String status;
		
		try {
			service.check(brackets);
			status = "All right!";
		} catch (Exception e) {
			status = e.getMessage();
		}
		
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("status", status);
		
		return mv;
	}
	
}
