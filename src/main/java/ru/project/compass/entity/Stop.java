package ru.project.compass.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Stop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(mappedBy = "stops", cascade = CascadeType.ALL)
    private Set<Direction> directions;

    private String title;
    private Float N;
    private Float E;

    public Stop() {
    }

    public Stop(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Direction> getDirections() {
        return directions;
    }

    public void setDirections(Set<Direction> directions) {
        this.directions = directions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getN() {
        return N;
    }

    public void setN(Float n) {
        N = n;
    }

    public Float getE() {
        return E;
    }

    public void setE(Float e) {
        E = e;
    }
}
