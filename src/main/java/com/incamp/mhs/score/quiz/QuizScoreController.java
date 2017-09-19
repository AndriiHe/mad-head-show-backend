package com.incamp.mhs.score.quiz;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/games/{gameId}/round/{roundIndex}/quiz")
@Transactional
public class QuizScoreController {

    private final QuizScoreService quizScoreService;

    @Autowired
    public QuizScoreController(QuizScoreService quizScoreService) {
        this.quizScoreService = quizScoreService;
    }

    @GetMapping("/{quizIndex}")
    @JsonView(QuizScore.MinimalView.class)
    public List<QuizScore> getByQuizIndex(@PathVariable Map<String, String> pathVars) {
        Integer gameId = Integer.parseInt(pathVars.get("gameId"));
        Integer roundIndex = Integer.parseInt(pathVars.get("roundIndex"));
        Integer quizIndex = Integer.parseInt(pathVars.get("quizIndex"));
        return quizScoreService.getByRoundIndexAndQuizIndex(gameId, roundIndex, quizIndex);
    }

    @PostMapping("/{quizIndex}")
    public void saveQuizzes(@PathVariable Map<String, String> pathVars, @RequestBody List<QuizScoreForm> quizScoreForm) {
        for (QuizScoreForm qForm : quizScoreForm) {

            qForm.setGameId(Long.parseLong(pathVars.get("gameId")));
            qForm.setRoundIndex(Integer.parseInt(pathVars.get("roundIndex")));
            qForm.setQuizIndex(Integer.parseInt(pathVars.get("quizIndex")));

            quizScoreService.save(qForm.toQuizScore());
        }
    }
}
