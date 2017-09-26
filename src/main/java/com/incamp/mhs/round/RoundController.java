package com.incamp.mhs.round;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public void setInGame(@PathVariable Long gameId, @RequestBody @Valid RoundForm roundForm) {
        roundService.save(gameId, roundForm.toRound());
    }

    @GetMapping("/{index}")
    @Transactional
    @JsonView(Round.MinimalView.class)
    public List<Round> getByIndex(@PathVariable Long gameId, @PathVariable Integer index) {
        return roundService.getByIndex(gameId, index);
    }

    @GetMapping
    @JsonView(Round.MinimalView.class)
    public List<Round> getByGameId(@PathVariable Long gameId) {
        return roundService.getByGameId(gameId);
    }
}
