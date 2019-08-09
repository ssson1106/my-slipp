package net.slipp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {
	
	@GetMapping("/helloworld")
	public ModelAndView welcome(int age) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("age", age);
		mav.addObject("name", "javajigi");
		mav.setViewName("welcome");
		return mav;
	} 
	
	
	
}
