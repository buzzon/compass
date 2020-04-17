package ru.naumen.compass.entity;

import javax.persistence.*;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Passenger passenger;

    @ManyToOne
    @JoinTable(name = "tickets_ticketstatus",
            joinColumns = @JoinColumn(name = "ticket_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ticketstatus_id", referencedColumnName = "id"))
    private Ticketstatus ticketstatus;

    @ManyToOne
    private Ride ride;

    private Integer seat;
    private Integer price;
    private Boolean isBenefit = false;
    private Boolean isChildren = false;
    private Boolean isLuggage = false;

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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean getBenefit() {
        return isBenefit;
    }

    public void setBenefit(Boolean benefit) {
        isBenefit = benefit;
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
