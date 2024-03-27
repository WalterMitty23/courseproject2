package com.example.courseproject2.repository;

import com.example.courseproject2.model.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionRepository implements QuestionRepository {
    private final Set<Question> storage = new HashSet<>();


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
