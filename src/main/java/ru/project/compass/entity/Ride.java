package ru.project.compass.entity;

import javax.persistence.*;
import java.util.*;

@Entity
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinTable(name = "rides_template",
            joinColumns = @JoinColumn(name = "ride_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "template_id", referencedColumnName = "id"))
    private Template template;

    @OneToMany(mappedBy = "ride")
    private Set<Ticket> tickets = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "rides_discounts",
            joinColumns = @JoinColumn(name = "rides_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "discounts_id", referencedColumnName = "id"))
    private Set<Discount> discounts;

    @Temporal(TemporalType.DATE)
    private Date departureDate;
    private Long views = 0L;
    private Boolean isValid;

    public void addTicket(Ticket ticket) {
        ticket.setRide(this);
        tickets.add(ticket);
    }

    public int getTicketCount() {
        return tickets.size();
    }

    public List<Integer> getFreeSeats() {

        Integer seatsCount = getTemplate().getCountTickets();
        List<Integer> freeSeats = new ArrayList<>(seatsCount);
        List<Integer> takenSeats = new ArrayList<>(seatsCount);

        for (int i = 0; i < seatsCount; i++)
            freeSeats.add(i);

        for (Ticket ticket : tickets)
            takenSeats.add(ticket.getSeat());

        freeSeats.removeAll(takenSeats);
        return freeSeats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Set<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(Set<Discount> discounts) {
        this.discounts = discounts;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }
}
