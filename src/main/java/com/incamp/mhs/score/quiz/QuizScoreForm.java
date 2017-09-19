package com.incamp.mhs.score.quiz;

import com.incamp.mhs.game.Game;
import com.incamp.mhs.team.Team;
import lombok.Data;

@Data
public class QuizScoreForm {

    private Long gameId;

    private Integer roundIndex;

    private Integer quizIndex;

    private Long teamId;

    private Double score;

    public QuizScore toQuizScore() {
        QuizScore quizScore = new QuizScore();
        quizScore.setRoundIndex(roundIndex);
        quizScore.setQuizIndex(quizIndex);
        quizScore.setScore(score);

        Team team = new Team();
        team.setId(teamId);
        quizScore.setTeam(team);

        Game game = new Game();
        game.setId(gameId);
        quizScore.setGame(game);

        return quizScore;
    }
}
