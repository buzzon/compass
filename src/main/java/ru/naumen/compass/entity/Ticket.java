package ru.naumen.compass.entity;

import javax.persistence.*;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinTable(name = "tickets_passenger",
            joinColumns = @JoinColumn(name = "ticket_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "passenger_id", referencedColumnName = "id"))
    private Passenger passenger;

    @ManyToOne
    @JoinTable(name = "tickets_ticketstatus",
            joinColumns = @JoinColumn(name = "ticket_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ticketstatus_id", referencedColumnName = "id"))
    private Ticketstatus ticketstatus;

    @ManyToOne
    @JoinTable(name = "tickets_ride",
            joinColumns = @JoinColumn(name = "ticket_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ride_id", referencedColumnName = "id"))
    private Ride ride;

    private Integer seat;
    private Float price;
    private Boolean isChildren = false;
    private Boolean isLuggage = false;

    public void Config(Ride ride, Passenger passenger, Integer seat, Ticketstatus status) {
        ride.addTicket(this);
        passenger.addTicket(this);
        status.addTickets(this);

        this.seat = seat;
        calculatePrice();
    }

    private void calculatePrice(){
        Pricing pricing = getCarrier().getPricing();
        float ChildMultiplier = isChildren ? pricing.getChildren_multiplier() : 0;
        float LuggageMultiplier = isLuggage ? pricing.getLuggage_multiplier() : 0;
        price = ride.getTemplate().getPrice() * (1 + LuggageMultiplier +ChildMultiplier);
    }

    public Carrier getCarrier() {
        return ride.getTemplate().getCarrier();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Ticketstatus getTicketstatus() {
        return ticketstatus;
    }

    public void setTicketstatus(Ticketstatus ticketstatus) {
        this.ticketstatus = ticketstatus;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Boolean getChildren() {
        return isChildren;
    }

    public void setChildren(Boolean children) {
        isChildren = children;
    }

    public Boolean getLuggage() {
        return isLuggage;
    }

    public void setLuggage(Boolean luggage) {
        isLuggage = luggage;
    }
}
