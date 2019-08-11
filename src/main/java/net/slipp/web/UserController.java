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
	/*
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
		User sessionedUser = (User)session.getAttribute(HttpSessionUtils.USER_SESSION_KEY); 
		//Optional<User> user = userRepository.findById(id);
		//자신의 정보만 수정할수 있도록 수정
		if(sessionedUser == null) {//로그인 상태인지만 체크
			return "redirect:/user/loginForm";
		}
		
		if(id != sessionedUser.getId()) {//자신의 아이디인지 체크
			throw new IllegalStateException("자신의 정보만 수정할 수 있습니다.");
		}
		
		model.addAttribute("user", sessionedUser);
		return "/user/updateForm";
	}*/
	
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session)) {//로그인 상태인지만 체크
			return "redirect:/user/loginForm";
		}
		User sessionedUser = HttpSessionUtils.getUserFromSession(session);
		if(!sessionedUser.matchId(id)) {//자신의 아이디인지 체크
			throw new IllegalStateException("자신의 정보만 수정할 수 있습니다.");
		}
		
		model.addAttribute("user", sessionedUser);
		return "/user/updateForm";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, Model model, User updateUser,
				HttpSession session) {
		System.out.println("update");
		if(!HttpSessionUtils.isLoginUser(session)) {//로그인 상태인지만 체크
			return "redirect:/user/loginForm";
		}
		User sessionedUser = HttpSessionUtils.getUserFromSession(session);
		if(!sessionedUser.matchId(id)) {//자신의 아이디인지 체크
			throw new IllegalStateException("자신의 정보만 수정할 수 있습니다.");
		}
		
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
		
		if(user == null) {
			return "redirect:/user/loginForm";
		}
		if(!user.matchPassword(password)) {
			return "redirect:/user/loginForm";
		}
		
		System.out.println("Login Success");
		session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
