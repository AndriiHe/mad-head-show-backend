package com.incamp.mhs.season;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/seasons")
@Transactional
public class SeasonController {

    private final SeasonService seasonService;

    public SeasonController(SeasonService seasonService) {
        this.seasonService = seasonService;
    }

    @GetMapping("{id}")
    @JsonView(Season.WithGames.class)
    public Season getById(@PathVariable long id) {
        return seasonService.getById(id);
    }

    @PostMapping()
    public void createSeason(@RequestBody @Valid SeasonForm seasonForm) {
        Season season = seasonForm.toSeason();
        seasonService.save(season);
    }
}
