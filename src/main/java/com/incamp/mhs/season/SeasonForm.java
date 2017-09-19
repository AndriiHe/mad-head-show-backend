package com.incamp.mhs.season;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SeasonForm {
    @NotBlank
    private String name;


    public Season toSeason(){
        Season season = new Season();
        season.setName(name);
        return season;
    }
}
