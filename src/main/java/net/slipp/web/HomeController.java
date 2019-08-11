package net.slipp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import net.slipp.domain.QuestionRepostory;

@Controller
public class HomeController {
	@Autowired
	private QuestionRepostory questionRepostory;
	
	@GetMapping("/")
	public String home(Model model) {
		System.out.println("home");
		model.addAttribute("questions", questionRepostory.findAll());
		
		return "index";
	}
	
	
}
