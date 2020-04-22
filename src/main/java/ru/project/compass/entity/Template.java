package ru.project.compass.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
public class Template {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "template")
    private Set<Ride> rides = new HashSet<>();

    @ManyToOne
    @JoinTable(name = "templates_carrier",
            joinColumns = @JoinColumn(name = "carrier_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "template_id", referencedColumnName = "id"))
    private Carrier carrier;

    @ManyToOne
    @JoinTable(name = "templates_transport",
            joinColumns = @JoinColumn(name = "template_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "transport_id", referencedColumnName = "id"))
    private Transport transport;

    @ManyToMany
    @JoinTable(name = "templates_stops",
            joinColumns = @JoinColumn(name = "template_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "stop_id", referencedColumnName = "id"))
    private List<Stop> stops = new LinkedList<>();

    private Integer countTickets;
    private Float price;

    public void addChildrenRide(Ride ride) {
        ride.setTemplate(this);
        rides.add(ride);
    }

    public void addStop(Stop stop){
        stops.add(stop);
    }

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

    public Carrier getCarrier() {
        return carrier;
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public Integer getCountTickets() {
        return countTickets;
    }

    public void setCountTickets(Integer countTickets) {
        this.countTickets = countTickets;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
