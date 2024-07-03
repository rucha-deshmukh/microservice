package com.questions.services;

import com.questions.entities.Question;

import java.util.List;

public interface QuestionService {

    Question add(Question Question);
    List<Question> get();
    Question get(Long id);
    List<Question> getQuestionsForQuiz(Long quizId);
}
