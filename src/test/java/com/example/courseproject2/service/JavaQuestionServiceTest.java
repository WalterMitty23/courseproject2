package com.example.courseproject2.service;

import com.example.courseproject2.exceptions.NotFoundQuestionsException;
import com.example.courseproject2.model.Question;
import com.example.courseproject2.repository.JavaQuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class JavaQuestionServiceTest {

    static List<Question> QUESTIONS = List.of(
            new Question("test_question1", "test_answer11"),
            new Question("test_question2", "test_answer22"),
            new Question("test_question3", "test_answer33"),
            new Question("test_question4", "test_answer44")
    );

            @Mock
    JavaQuestionRepository repository;

    @InjectMocks
    JavaQuestionService service;

    @BeforeEach
    void setUp() {
        when(repository.getAll()).thenReturn(QUESTIONS);
    }

    @Test
    void testRandomQuestion() {
        for (int i = 0; i < 100; i++) {
            assertTrue(QUESTIONS.contains(service.getRandomQuestion()));
        }
    }

    @Test
    void testEmptyQuestions() {
        when(repository.getAll()).thenReturn(List.of());
        assertThrows(NotFoundQuestionsException.class, () -> service.getRandomQuestion());
    }



}