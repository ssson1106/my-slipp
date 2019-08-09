package net.slipp.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	private List<User> users = new ArrayList<User>();
	
	@PostMapping("/create")
	public ModelAndView create(User user) {
		ModelAndView mav = new ModelAndView();
		System.out.println("create user = " + user);
		users.add(user);
		mav.setViewName("redirect:list");
		return mav;
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		
		model.addAttribute("users", users);
		return "list";
	}
	
}
