package ru.naumen.compass.entity;

import javax.persistence.*;

@Entity
public class Ticket {
    @Id
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "passenger_ticket",
            joinColumns =
                    { @JoinColumn(name = "id_ticket", referencedColumnName = "id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "id_passenger", referencedColumnName = "id") })
    private Passenger passenger;

    private Integer seat;
    private Float price;
    private Boolean isBenefit;
    private Boolean isChildren;
    private Boolean isLuggage;
}
