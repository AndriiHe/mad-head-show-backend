package com.incamp.mhs.entity;

import com.fasterxml.jackson.annotation.JsonView;
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
    private String fullName;

    @JsonView(MinimalView.class)
    private String Phone;

    @JsonView(MinimalView.class)
    private Integer teamSize;

    @JsonView(MinimalView.class)
    private Date date;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Team team;

    public interface MinimalView {}
}
