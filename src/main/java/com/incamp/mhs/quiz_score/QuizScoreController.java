package com.incamp.mhs.quiz_score;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuizScoreController {

    private final QuizScoreService quizScoreService;

    @Autowired
    public QuizScoreController(QuizScoreService quizScoreService) {
        this.quizScoreService = quizScoreService;
    }
}
