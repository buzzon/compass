package ru.naumen.compass.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Carrier carrier;

    @OneToMany
    @JoinColumn(name = "template_id")
    private Set<Ride> rides;

    private Integer countTickets;
    private Float price;

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

    public Set<Ride> getRides() {
        return rides;
    }

    public void setRides(Set<Ride> rides) {
        this.rides = rides;
    }

    public Integer getCountTickets() {
        return countTickets;
    }

    public void setCountTickets(Integer countTickets) {
        this.countTickets = countTickets;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
