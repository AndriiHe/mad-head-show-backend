package com.incamp.mhs.score.quiz;

import com.fasterxml.jackson.annotation.JsonView;
import com.incamp.mhs.game.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/games/{gameId}")
@Transactional
public class QuizScoreController {

    private final QuizScoreService quizScoreService;
    private final GameService gameService;

    @Autowired
    public QuizScoreController(QuizScoreService quizScoreService, GameService gameService) {
        this.quizScoreService = quizScoreService;
        this.gameService = gameService;
    }

    @GetMapping("/round/{roundIndex}/quiz/{quizIndex}")
    @JsonView(QuizScore.MinimalView.class)
    public List<QuizScore> getByQuizIndex(@PathVariable Map<String, String> pathVars) {
        Long gameId = Long.parseLong(pathVars.get("gameId"));
        Integer roundIndex = Integer.parseInt(pathVars.get("roundIndex"));
        Integer quizIndex = Integer.parseInt(pathVars.get("quizIndex"));
        return quizScoreService.getByRoundIndexAndQuizIndex(gameId, roundIndex, quizIndex);
    }

    @PostMapping("/round/{roundIndex}/quiz/{quizIndex}")
    public void saveQuizzes(@PathVariable Map<String, String> pathVars, @RequestBody List<QuizScoreForm> quizScoreForm) {
        for (QuizScoreForm qForm : quizScoreForm) {

            qForm.setGameId(Long.parseLong(pathVars.get("gameId")));
            qForm.setRoundIndex(Integer.parseInt(pathVars.get("roundIndex")));
            qForm.setQuizIndex(Integer.parseInt(pathVars.get("quizIndex")));

            QuizScore quizScore = qForm.toQuizScore();
            quizScore.setGame(gameService.getById(qForm.getGameId()));

            quizScoreService.save(quizScore);
        }
    }

    @GetMapping("/round/{roundIndex}/quiz")
    @JsonView(QuizScore.MinimalView.class)
    public List<QuizScore> getByRoundIndex(@PathVariable Map<String, String> pathVars) {
        Long gameId = Long.parseLong(pathVars.get("gameId"));
        Integer roundIndex = Integer.parseInt(pathVars.get("roundIndex"));

        return quizScoreService.getByRoundIndex(gameId, roundIndex);
    }

    @GetMapping("/quiz")
    @JsonView(QuizScore.MinimalView.class)
    public List<QuizScore> getByGameId(@PathVariable Long gameId) {
        return quizScoreService.getByGameId(gameId);
    }

    @GetMapping("/teams/{teamId}/quiz")
    @JsonView(QuizScore.MinimalView.class)
    public List<QuizScore> getByTeamId(@PathVariable Map<String, String> pathVars) {
        Long gameId = Long.parseLong(pathVars.get("gameId"));
        Long teamId = Long.parseLong(pathVars.get("teamId"));

        return quizScoreService.getByTeamId(gameId, teamId);
    }
}
