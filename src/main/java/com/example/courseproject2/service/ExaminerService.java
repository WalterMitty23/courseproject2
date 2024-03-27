package com.example.courseproject2.service;

import com.example.courseproject2.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestion(int amount);

}
