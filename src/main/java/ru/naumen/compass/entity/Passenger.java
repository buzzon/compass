package ru.naumen.compass.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "passenger")
    private User user;

    @OneToMany(mappedBy = "passenger")
    private Set<Ticket> tickets = new HashSet<>();

    private String l_name;
    private String f_name;
    private String m_name;
    private Float rating;

    public void addTicket(Ticket ticket) {
        ticket.setPassenger(this);
        tickets.add(ticket);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}