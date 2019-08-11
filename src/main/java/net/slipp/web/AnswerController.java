package net.slipp.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.slipp.domain.Answer;
import net.slipp.domain.AnswerRepostory;
import net.slipp.domain.Question;
import net.slipp.domain.QuestionRepostory;
import net.slipp.domain.User;

@Controller
@RequestMapping("/questions/{questionsId}/answers")
public class AnswerController {
	
	@Autowired
	private AnswerRepostory answerRepostory;
	
	@Autowired
	private QuestionRepostory questionRepostory;
	
	@PostMapping("")
	public String create(@PathVariable Long questionsId, String contents, HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/user/loginForm";
		}
		
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		Question question = questionRepostory.getOne(questionsId);
		Answer newAnswer = new Answer(loginUser, question, contents);
		answerRepostory.save(newAnswer);
		return String.format("redirect:/questions/%d", questionsId);
	}
}
