package com.app.quizapp.controller;

import com.app.quizapp.dto.QuizQuestionResponse;
import com.app.quizapp.dto.QuizResultResponse;
import com.app.quizapp.dto.QuizSubmitRequest;
import com.app.quizapp.model.QuizQuestion;
import com.app.quizapp.service.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    private final QuizService service;

    public QuizController(QuizService service) {
        this.service = service;
    }

    @GetMapping("/questions")
    public ResponseEntity<List<QuizQuestionResponse>> getQuestions() {
        return ResponseEntity.ok(service.getQuestions());
    }

    @PostMapping("/submit")
    public ResponseEntity<QuizResultResponse> submitQuiz(
            @RequestBody QuizSubmitRequest request) {
        return ResponseEntity.ok(service.submitQuiz(request));
    }

    @PostMapping("/question")
    public ResponseEntity<String> addQuestion(@RequestBody QuizQuestion question) {
        service.addQuestion(question);
        return new ResponseEntity<>("Question added successfully", HttpStatus.CREATED);
    }
}
