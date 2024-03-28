package com.example.courseproject2.service;

import com.example.courseproject2.exceptions.NotEnoughQuestionsException;
import com.example.courseproject2.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    static final List<Question> MATH_QUESTIONS = List.of(
            new Question("Math_Question_1", "Math_Answer_1"),
            new Question("Math_Question_2", "Math_Answer_2")
    );

    static final List<Question> JAVA_QUESTIONS = List.of(
            new Question("Java_Question_1", "Java_Question_1"),
            new Question("Java_Question_2", "Java_Question_2")
    );

    @Mock
    JavaQuestionService javaQuestionService;

    @Mock
    MathQuestionService mathQuestionService;

    @InjectMocks
    ExaminerServiceImpl examinerService;

    @BeforeEach
     void setUp() {
        when(javaQuestionService.getAll()).thenReturn(JAVA_QUESTIONS);
        when(mathQuestionService.getAll()).thenReturn(MATH_QUESTIONS);
        examinerService = new ExaminerServiceImpl(javaQuestionService, mathQuestionService);
    }

    @Test
    void testRandomQuestions() {
        when(javaQuestionService.getRandomQuestion()).thenReturn(JAVA_QUESTIONS.get(0));
        when(mathQuestionService.getRandomQuestion())
                .thenReturn(MATH_QUESTIONS.get(0))
                .thenReturn(MATH_QUESTIONS.get(1));

        var actual = examinerService.getQuestion(3);

        assertThat(actual).containsExactlyInAnyOrder(
                JAVA_QUESTIONS.get(0),
                MATH_QUESTIONS.get(0),
                MATH_QUESTIONS.get(1)
        );
    }

    @Test
    void testNotEnoughQuestions() {
        assertThrows(NotEnoughQuestionsException.class, () -> examinerService.getQuestion(100000));
    }
}