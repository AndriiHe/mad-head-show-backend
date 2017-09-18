package com.incamp.mhs.round;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/games/{gameId}/rounds")
@Transactional
public class RoundController {

    private final RoundService roundService;

    @Autowired
    public RoundController(RoundService roundService) {
        this.roundService = roundService;
    }

    @PostMapping
    public void setRounds(@PathVariable("gameId") long gameId, @RequestBody RoundForm roundForm) {
        Round round = roundForm.toRound();
        roundService.save(round);
    }

    @GetMapping("/{index}")
    @Transactional
    @JsonView(Round.MinimalView.class)
    public List<Round> getByIndex(@PathVariable("gameId") Long gameId, @PathVariable("index") Integer index) {
        return roundService.getByIndex(gameId, index);
    }
}
