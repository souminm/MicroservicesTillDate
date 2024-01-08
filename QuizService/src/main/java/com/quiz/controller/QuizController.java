package com.quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.entity.Quiz;
import com.quiz.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {
  
	
	@Autowired
	private QuizService quizService;

	@Autowired
	public QuizController(QuizService quizService) {
		this.quizService = quizService;
	}
	
	@PostMapping()
	public Quiz create(@RequestBody Quiz quiz) {
		return quizService.add(quiz);
	}
	
	@GetMapping()
	public List<Quiz> getAll(){
		return quizService.get();
	}
	
	@GetMapping("/{id}")
	public Quiz getQuiz(@PathVariable Long id) {
		return quizService.get(id);
	}
	
	
}
