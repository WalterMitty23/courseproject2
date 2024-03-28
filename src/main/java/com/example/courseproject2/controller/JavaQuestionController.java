package com.example.courseproject2.controller;

import com.example.courseproject2.model.Question;
import com.example.courseproject2.service.QuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/java")
public class JavaQuestionController {
    private final QuestionService service;

    public JavaQuestionController(@Qualifier("java") QuestionService service) {
        this.service = service;
    }

    @GetMapping("/add")
    public Question addQuestion(@RequestParam String question, @RequestParam String answer) {
        return service.add(question, answer);
    }

    @GetMapping("/remove")
    public Question remove(@RequestParam String question, @RequestParam String answer) {
        return service.remove(new Question(question, answer));
    }

    @GetMapping("/getAll")
    public Collection<Question> getAll() {
        return service.getAll();
    }
}
