package net.slipp.web;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.slipp.domain.Question;
import net.slipp.domain.QuestionRepostory;
import net.slipp.domain.User;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	
	@Autowired
	private QuestionRepostory questionRepository;
	
	@GetMapping("/form")
	public String form(HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "/user/loginForm";
		}
		return "/qna/form";
	}
	
	@PostMapping("")
	public String create(String title, String contents, HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/user/loginForm";
		}
		
		User sessionedUser = HttpSessionUtils.getUserFromSession(session);
		Question newQuestion = new Question(sessionedUser,
				title, contents);
		questionRepository.save(newQuestion);
		return "redirect:/";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable Long id, Model model) {
		Optional<Question> optionalQuestion = questionRepository.findById(id);
		model.addAttribute("question", optionalQuestion.get());
		return "/qna/show";
	}
	
	@GetMapping("/{id}/form")
	public String form(@PathVariable Long id, HttpSession session, Model model) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/user/loginForm";
		}
		model.addAttribute("question", questionRepository.findById(id).get());
		return "/qna/updateForm";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, String title, String contents, HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/user/loginForm";
		}
		Question question = questionRepository.getOne(id);
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		if(!question.isSameWriter(loginUser)) {
			return "redirect:/user/loginForm";
		}
		question.update(title, contents);
		questionRepository.save(question);
		return "redirect:/questions/"+id;
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id, HttpSession session) {
		System.out.println("delete question = "+id);
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/user/loginForm";
		}
		Question question = questionRepository.getOne(id);
		System.out.println(question);
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		System.out.println(loginUser);
		if(!question.isSameWriter(loginUser)) {
			return "redirect:/user/loginForm";
		}
		questionRepository.deleteById(id);
		return "redirect:/";
	}
}
