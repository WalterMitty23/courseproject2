package com.example.courseproject2.repository;

import com.example.courseproject2.model.Question;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
@Repository("mathRepository")
public class MathQuestionRepository implements QuestionRepository{
    private final Set<Question> storage = new HashSet<>();

    @PostConstruct
    private void init() {
        storage.add(new Question("math_question1", "math_answer1"));
        storage.add(new Question("math_question2", "math_answer2"));
        storage.add(new Question("math_question3", "math_answer3"));
    }

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));

    }

    @Override
    public Question add(Question question) {
        storage.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (storage.remove(question)) {
            return question;
        }
        return null;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(storage);
    }

}
