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
import net.slipp.domain.Result;
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
		Question question = questionRepository.getOne(id);
		Result result = valid(session, question);
		if( !result.isValid() ) {
			System.out.println("fail \n" + result);
			model.addAttribute("result", result);
			return "/user/login";
		}
		model.addAttribute("question", questionRepository.findById(id).get());
		return "/qna/updateForm";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, String title, String contents, 
			HttpSession session, Model model) {
		Question question = questionRepository.getOne(id);
		Result result = valid(session, question);
		if( !result.isValid() ) {
			System.out.println("fail \n" + result);
			model.addAttribute("result", result);
			return "/user/login";
		}
		question.update(title, contents);
		questionRepository.save(question);
		return "redirect:/questions/"+id;
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id, HttpSession session, Model model) {
		Question question = questionRepository.findById(id).get();
		Result result = valid(session, question);
		if( !result.isValid() ) {
			model.addAttribute("result", result);
			return "/user/login";
		}
		System.out.println("delete\n"+question);
		questionRepository.delete(question);
		
		return "redirect:/";
	}
	
	public Result valid(HttpSession session, Question question) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			return Result.fail("로그인이 필요합니다.");
		}
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		if(!question.isSameWriter(loginUser)) {
			return Result.fail("자신이 쓴 글만 수정, 삭제가 가능합니다.");
		}
		return Result.ok();
	}
	/*
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, String title, String contents, 
			HttpSession session, Model model) {
		try {
			Question question = questionRepository.getOne(id);
			hasPermission(session, question);
			question.update(title, contents);
			questionRepository.save(question);
			return "redirect:/questions/"+id;
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "redirect:/user/loginForm";
		}
		
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id, HttpSession session, Model model) {
		try {
			Question question = questionRepository.getOne(id);
			hasPermission(session, question);
			questionRepository.deleteById(id);
			return "redirect:/";
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "redirect:/user/loginForm";
		}
	}
	public void hasPermission(HttpSession session, Question question) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			throw new IllegalStateException("로그인이 필요합니다.");
		}
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		if(!question.isSameWriter(loginUser)) {
			throw new IllegalStateException("자신이 쓴 글만 수정, 삭제가 가능합니다.");
		}
	}
	*/
	
}

