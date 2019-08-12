package net.slipp.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.slipp.domain.Answer;
import net.slipp.domain.AnswerRepostory;
import net.slipp.domain.Question;
import net.slipp.domain.QuestionRepostory;
import net.slipp.domain.Result;
import net.slipp.domain.User;

@RestController
@RequestMapping("/api/questions/{questionId}/answers")
public class ApiAnswerController {
	
	@Autowired
	private AnswerRepostory answerRepostory;
	
	@Autowired
	private QuestionRepostory questionRepostory;
	
	@PostMapping("")
	public Answer create(@PathVariable Long questionId, String contents, HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			return null;
		}
		
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		Question question = questionRepostory.findById(questionId).get();
		Answer newAnswer = new Answer(loginUser, question, contents);
		question.addAnswer();
		answerRepostory.save(newAnswer);
		return newAnswer;
	}
	
	@DeleteMapping("/{answerId}")
	public Result delete(@PathVariable Long questionId, @PathVariable Long answerId, HttpSession session) {
		System.out.println("answer delete");
		if(!HttpSessionUtils.isLoginUser(session)) {
			return Result.fail("로그인 하세요.");
		}
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		Answer answer = answerRepostory.findById(answerId).get();
		if(!answer.isSameWriter(loginUser)) {
			return Result.fail("자신의 답변만 삭제할 수 있습니다.");
		}
		
		answerRepostory.delete(answer);
		Question question = questionRepostory.findById(questionId).get();
		question.deleteAnswer();
		questionRepostory.save(question);
		return Result.ok();
	}
	
}
