package com.incamp.mhs.request;

import com.fasterxml.jackson.annotation.JsonView;
import com.incamp.mhs.team.Team;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(MinimalView.class)
    private Long id;

    @JsonView(MinimalView.class)
    private String captainName;

    @JsonView(MinimalView.class)
    private String phone;

    @JsonView(MinimalView.class)
    private Integer teamSize;

    @JsonView(MinimalView.class)
    private Date date;

    @JsonView(MinimalView.class)
    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

    public interface MinimalView {}
}
