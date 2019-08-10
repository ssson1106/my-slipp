package net.slipp.web;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.slipp.domain.User;
import net.slipp.domain.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/form")
	public String form() {
		System.out.println("form");
		return "/user/form";
	}
	
	@PostMapping("")
	public String create(User user) {
		System.out.println("create user = " + user);
		userRepository.save(user);
		return "redirect:/user";
	}
	
	@GetMapping("")
	public String list(Model model) {
		System.out.println("get /users list");
		model.addAttribute("users", userRepository.findAll());
		return "/user/list";
	}
	
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model) {
		System.out.println("updateForm "+id);
		Optional<User> user = userRepository.findById(id);
		model.addAttribute("user", user.get());
		return "/user/updateForm";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, Model model, User updateUser) {
		System.out.println("update");
		User user = userRepository.getOne(id);
		user.update(updateUser);
		userRepository.save(user);
		return "redirect:/user";
	}
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "/user/login";
	}
	
	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session) {
		User user = userRepository.findByUserId(userId);
		System.out.println(user);
		if(user == null) {
			return "redirect:/user/loginForm";
		}
		if(!password.equals(user.getPassword())) {
			return "redirect:/user/loginForm";
		}
		System.out.println("success");
		session.setAttribute("user", user);
		return "redirect:/";
	}
}
