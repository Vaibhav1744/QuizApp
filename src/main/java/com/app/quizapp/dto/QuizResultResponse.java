package com.app.quizapp.dto;

public class QuizResultResponse {
    private int score;
    private int total;

    public QuizResultResponse(int score, int total) {
        this.score = score;
        this.total = total;
    }

    public int getScore() {
        return score;
    }

    public int getTotal() {
        return total;
    }
}
