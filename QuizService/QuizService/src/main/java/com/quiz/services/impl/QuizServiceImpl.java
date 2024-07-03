package com.quiz.services.impl;

import com.quiz.entities.Quiz;
import com.quiz.repositories.QuizRepository;
import com.quiz.services.QuestionClient;
import com.quiz.services.QuizService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;
    private final QuestionClient questionClient;


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
        List<Quiz> quizList = quizRepository.findAll();
        return quizList.stream().map(quiz ->
        {
            quiz.setQuestions(questionClient.getQuestionsForQuiz(quiz.getId()));
            return quiz;
        }).collect(Collectors.toList());
    }

    @Override
    public Quiz get(Long id) {
        Quiz quiz=quizRepository.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found"));
        quiz.setQuestions(questionClient.getQuestionsForQuiz(quiz.getId()));
        return quiz;
    }
}
