package com.example.demo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HelloController {
	
	@RequestMapping(value="/test", method= RequestMethod.GET)
	public ModelAndView main() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("list");
		mav.addObject("text", "world");
        return mav;
    }
}
