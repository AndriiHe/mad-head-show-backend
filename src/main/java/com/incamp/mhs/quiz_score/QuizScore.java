package com.incamp.mhs.quiz_score;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class QuizScore {

    @Id
    @JsonView(MinimalView.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonView(MinimalView.class)
    private Double score;

    public QuizScore(Double score) {
        this.score = score;
    }

    public interface MinimalView {}
}
