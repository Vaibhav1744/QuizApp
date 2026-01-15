package com.app.quizapp.service;
import com.app.quizapp.dto.QuizQuestionResponse;
import com.app.quizapp.dto.QuizResultResponse;
import com.app.quizapp.dto.QuizSubmitRequest;
import com.app.quizapp.model.QuizQuestion;
import com.app.quizapp.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class QuizService {
    private final QuizRepository repository;

    public QuizService(QuizRepository repository) {
        this.repository = repository;
    }

    public List<QuizQuestionResponse> getQuestions() {
        List<QuizQuestion> questions = repository.findAll();
        Collections.shuffle(questions);

        List<QuizQuestionResponse> response = new ArrayList<>();
        for (QuizQuestion q : questions) {
            QuizQuestionResponse dto = new QuizQuestionResponse();
            dto.setId(q.getId());
            dto.setQuestion(q.getQuestion());
            dto.setOptions(
                    List.of(q.getOptionA(), q.getOptionB(), q.getOptionC(), q.getOptionD())
            );
            response.add(dto);
        }
        return response;
    }

    public QuizResultResponse submitQuiz(QuizSubmitRequest request) {
        int score = 0;
        int total = request.getAnswers().size();

        for (Map.Entry<Integer, String> entry : request.getAnswers().entrySet()) {
            QuizQuestion question = repository.findById(entry.getKey())
                    .orElseThrow(() -> new RuntimeException("Invalid Question ID"));

            if (question.getCorrectOption().equalsIgnoreCase(entry.getValue())) {
                score++;
            }
        }

        repository.saveResult("anonymous", score, total);

        return new QuizResultResponse(score, total);
    }

    public void addQuestion(QuizQuestion question) {
        repository.save(question);
    }
}
