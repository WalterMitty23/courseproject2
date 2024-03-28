package com.example.courseproject2.service;

import com.example.courseproject2.exceptions.NotEnoughQuestionsException;
import com.example.courseproject2.model.Question;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService mathService;
    private final QuestionService javaService;
    private final Random random = new Random();


    public ExaminerServiceImpl(@Qualifier("math") QuestionService mathService,
                               @Qualifier("java") QuestionService javaService) {
        this.mathService = mathService;
        this.javaService = javaService;
    }

    @Override
    public Collection<Question> getQuestion (int amount) {
        var allQuestions = new ArrayList<>(mathService.getAll());
        allQuestions.addAll(javaService.getAll());

        if (amount > allQuestions.size()) {
            throw new NotEnoughQuestionsException();
        }
        if (amount == allQuestions.size()) {
            return allQuestions;
        }

        Set<Question> questions = new HashSet<>();
        while (questions.size() < amount) {
            var question = random.nextBoolean() ? mathService.getRandomQuestion() : javaService.getRandomQuestion();
            questions.add(question);
        }
        return questions;
    }

}
