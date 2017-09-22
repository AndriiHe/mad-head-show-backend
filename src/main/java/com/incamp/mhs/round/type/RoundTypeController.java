package com.incamp.mhs.round.type;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/roundTypes")
public class RoundTypeController {

    private final RoundTypeService roundTypeService;

    public RoundTypeController(RoundTypeService roundTypeService) {
        this.roundTypeService = roundTypeService;
    }

    @GetMapping("/{id}")
    public RoundType getById(@PathVariable Long id) {
        return roundTypeService.getById(id);
    }
}
