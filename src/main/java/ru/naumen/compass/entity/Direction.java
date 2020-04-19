package ru.naumen.compass.entity;

import javax.persistence.*;
import java.util.HashSet;
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
    private Set<Stop> stops;

    private Long id_source;
    private Long id_destination;

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

    public Set<Stop> getStops() {
        return stops;
    }

    public void setStops(Set<Stop> stops) {
        this.stops = stops;
    }

    public Long getId_source() {
        return id_source;
    }

    public void setId_source(Long id_source) {
        this.id_source = id_source;
    }

    public Long getId_destination() {
        return id_destination;
    }

    public void setId_destination(Long id_destination) {
        this.id_destination = id_destination;
    }
}

