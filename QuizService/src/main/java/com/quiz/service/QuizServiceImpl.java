package com.quiz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.entity.Question;
import com.quiz.entity.Quiz;
import com.quiz.repository.QuizRepository;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	private QuizRepository quizRepository;

	@Autowired
	private QuestionClient questionClient;

	public QuizServiceImpl(QuizRepository quizRepository, QuestionClient questionClient) {
		this.quizRepository = quizRepository;
		this.questionClient = questionClient;
	}

	@Override
	public Quiz add(Quiz quiz) {
		return quizRepository.save(quiz);
	}

	@Override
	public List<Quiz> get() {
		List<Quiz> quizzes = quizRepository.findAll();
		List<Quiz> updatedQuiz = new ArrayList<>();
//		for(Quiz q : quizzes) {
//			List<Question> questions = questionClient.getQuestionOfQuiz(q.getId());
//			Quiz test = new Quiz();
//			test.setId(q.getId());
//			test.setQuestions(questions);
//			test.setTitle(q.getTitle());
//			updatedQuiz.add(test);
//		}
//		return updatedQuiz;
		List<Quiz> updatedList = quizzes.stream().map(quiz -> {
			quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
			return quiz;
		}).collect(Collectors.toList());
		return updatedList;

	}

	@Override
	public Quiz get(Long id) {
		Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found!"));
		quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
		return quiz;
	}

}
