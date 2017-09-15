package com.incamp.mhs.quiz_score;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "quiz_scores")
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
