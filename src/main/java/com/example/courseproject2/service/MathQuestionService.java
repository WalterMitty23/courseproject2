package com.example.courseproject2.service;

import com.example.courseproject2.exceptions.NotEnoughQuestionsException;
import com.example.courseproject2.model.Question;
import com.example.courseproject2.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Random;
@Service
public class MathQuestionService implements  QuestionService {
    private final QuestionRepository repository;
    private final Random random = new Random();

    public MathQuestionService(QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Question add(String question, String answer) {
        return repository.add(new Question(question, answer));

    }

    @Override
    public Question add(Question question) {
        return repository.add(question) ;
    }

    @Override
    public Question remove(Question question) {
        return repository.remove(question) ;

    }

    @Override
    public Collection<Question> getAll() {
        return repository.getAll() ;
    }

    @Override
    public Question getRandomQuestion() {
        var questions = repository.getAll();
        var index = random.nextInt(questions.size());
        var i = 0;
        for (Question question : questions) {
            if (index == i) {
                return question;
            }
            i++;
        }
        throw new NotEnoughQuestionsException();
    }

}
