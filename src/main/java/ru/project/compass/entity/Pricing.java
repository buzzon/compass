package ru.project.compass.entity;

import javax.persistence.*;

@Entity
public class Pricing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "pricing")
    private Carrier carrier;

    private Short children_multiplier;
    private Short luggage_multiplier;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Carrier getCarrier() {
        return carrier;
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }

    public Short getChildren_multiplier() {
        return children_multiplier;
    }

    public void setChildren_multiplier(Short children_multiplier) {
        this.children_multiplier = children_multiplier;
    }

    public Short getLuggage_multiplier() {
        return luggage_multiplier;
    }

    public void setLuggage_multiplier(Short luggage_multiplier) {
        this.luggage_multiplier = luggage_multiplier;
    }
}
