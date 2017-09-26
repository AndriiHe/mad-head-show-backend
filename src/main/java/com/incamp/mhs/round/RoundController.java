package com.incamp.mhs.round;

import com.fasterxml.jackson.annotation.JsonView;
import com.incamp.mhs.game.Game;
import com.incamp.mhs.game.GameService;
import com.incamp.mhs.round.type.RoundType;
import com.incamp.mhs.round.type.RoundTypeService;
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
    private final GameService gameService;
    private final RoundTypeService roundTypeService;

    @Autowired
    public RoundController(RoundService roundService, GameService gameService, RoundTypeService roundTypeService) {
        this.roundService = roundService;
        this.gameService = gameService;
        this.roundTypeService = roundTypeService;
    }

    @PostMapping
    public void setRounds(@PathVariable("gameId") long gameId, @RequestBody @Valid RoundForm roundForm) {
        Round round = roundForm.toRound();
        Game game = gameService.getById(gameId);
        round.setGame(game);

        RoundType roundType = roundTypeService.getById(round.getRoundType().getId());
        round.setRoundType(roundType);

        roundService.save(round);
    }

    @GetMapping("/{index}")
    @Transactional
    @JsonView(Round.MinimalView.class)
    public List<Round> getByIndex(@PathVariable("gameId") Long gameId, @PathVariable("index") Integer index) {
        return roundService.getByIndex(gameId, index);
    }

    @GetMapping()
    public List<Round> getByGameId(@PathVariable("gameId") Long gameId) {
        return roundService.getByGameId(gameId);
    }
}
