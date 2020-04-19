package ru.naumen.compass.entity;

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
}
