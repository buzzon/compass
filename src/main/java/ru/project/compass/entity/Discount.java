package ru.project.compass.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(mappedBy = "discounts", cascade = CascadeType.ALL)
    private Set<Ride> rides;

    private String keyword;
    private Integer count;
    private Boolean isSingle;
    private Short multiplier;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Ride> getRides() {
        return rides;
    }

    public void setRides(Set<Ride> rides) {
        this.rides = rides;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Boolean getSingle() {
        return isSingle;
    }

    public void setSingle(Boolean single) {
        isSingle = single;
    }

    public Short getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(Short multiplier) {
        this.multiplier = multiplier;
    }
}
