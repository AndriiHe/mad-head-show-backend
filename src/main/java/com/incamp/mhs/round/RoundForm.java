package com.incamp.mhs.round;

import com.incamp.mhs.round.type.RoundType;
import lombok.Data;

@Data
public class RoundForm {

    private String name;

    private Long roundTypeId;

    private Integer index;

    private Integer numberOfQuizzes;

    public Round toRound() {
        Round round = new Round();
        round.setName(name);
        round.setIndex(index);
        round.setNumberOfQuizzes(numberOfQuizzes);

        RoundType roundType = new RoundType();
        roundType.setId(roundTypeId);
        round.setRoundType(roundType);
        return round;
    }
}
