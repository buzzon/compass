package ru.naumen.compass.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Ticketstatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "ticketstatus", cascade = CascadeType.ALL)
    private Set<Ticket> tickets;

    private String title;

    public Ticketstatus() {}

    public Ticketstatus(String title){
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> ticket) {
        this.tickets = ticket;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
