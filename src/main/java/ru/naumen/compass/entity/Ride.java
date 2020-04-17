package ru.naumen.compass.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Template template;

    @OneToMany
    @JoinColumn(name = "ride_id")
    private Set<Ticket> tickets;

    @ManyToMany
    @JoinTable(name = "rides_discounts",
            joinColumns = @JoinColumn(name = "rides_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "discounts_id", referencedColumnName = "id"))
    private Set<Discount> discounts;

    @Temporal(TemporalType.DATE)
    private Date departureDate;
    private Long views;
    private Boolean isValid;

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
