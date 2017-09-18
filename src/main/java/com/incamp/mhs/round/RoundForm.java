package com.incamp.mhs.round;

import com.incamp.mhs.game.Game;
import com.incamp.mhs.round.type.RoundType;
import lombok.Data;

@Data
public class RoundForm {
    String name;
    Long roundTypeId;
    Integer index;
    Integer numberOfQuizzes;
    Long gameId;

    public Round toRound() {
        Round round = new Round();
        round.setName(name);
        round.setIndex(index);
        round.setNumberOfQuizzes(numberOfQuizzes);

        Game game = new Game();
        game.setId(gameId);
        round.setGame(game);

        RoundType roundType = new RoundType();
        roundType.setId(roundTypeId);
        round.setRoundType(roundType);
        return round;
    }
}
