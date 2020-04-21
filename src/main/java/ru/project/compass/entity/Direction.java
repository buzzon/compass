package ru.project.compass.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Direction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "direction")
    private Set<Template> template = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "directions_stops",
            joinColumns = @JoinColumn(name = "direction_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "stop_id", referencedColumnName = "id"))
    private List<Stop> stops;

    public String stopsToString(){
        String stopsToString = "";

        for (Stop stop: stops)
            stopsToString += stop.getTitle() + " -> ";

        return stopsToString;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Template> getTemplate() {
        return template;
    }

    public void setTemplate(Set<Template> template) {
        this.template = template;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

}

