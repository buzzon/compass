package ru.project.compass.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Carrier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "carrier")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "carrier_pricing",
            joinColumns = @JoinColumn(name = "carrier_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "pricing_id", referencedColumnName = "id"))
    private Pricing pricing;

    @OneToMany(mappedBy = "carrier", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Template> templates = new HashSet<>();

    private String title;
    private Float rating;

    public void addChildTemplate(Template template) {
        template.setCarrier(this);
        templates.add(template);
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

    public Pricing getPricing() {
        return pricing;
    }

    public void setPricing(Pricing pricing) {
        this.pricing = pricing;
    }

    public Set<Template> getTemplates() {
        return templates;
    }

    public void setTemplates(Set<Template> templates) {
        this.templates = templates;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}
