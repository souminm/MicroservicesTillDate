package com.question.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.question.entity.Question;
import com.question.repository.QuestionRepository;


@Service
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public Question create(Question question) {
		// TODO Auto-generated method stub
		return questionRepository.save(question);
	}

	@Override
	public List<Question> get() {
		// TODO Auto-generated method stub
		return questionRepository.findAll();
	}

	@Override
	public Question getQuestion(Long id) {
		// TODO Auto-generated method stub
		return questionRepository.findById(id).orElseThrow(()->new RuntimeException("Question Not found!"));
	}

	@Override
	public List<Question> getQuestionOfQuiz(Long id) {
		// TODO Auto-generated method stub
		return questionRepository.findByQuizId(id);
	}

}
