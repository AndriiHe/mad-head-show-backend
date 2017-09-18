package com.incamp.mhs.game;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
@Transactional
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("{id}")
    @JsonView(Game.WithRequests.class)
    public Game getById(@PathVariable long id) {
        return gameService.getById(id);
    }

    @GetMapping
    @JsonView(Game.MinimalView.class)
    public List<Game> getAll() {
        return gameService.findBy(new GameSpecification());
    }

    @GetMapping("/location/{location}")
    @JsonView(Game.MinimalView.class)
    public List<Game> getByLoc(@PathVariable String location) {
        return gameService.findByLocation(location);
    }

    @PostMapping()
    public void createGame(@RequestBody GameForm gameForm) {
        Game game = gameForm.toGame();
        gameService.save(game);
    }
}
