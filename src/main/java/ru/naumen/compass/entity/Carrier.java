package ru.naumen.compass.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Carrier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "carrier")
    private User user;

    private String title;
    private Integer rating;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carrier carrier = (Carrier) o;
        return Objects.equals(id, carrier.id) &&
                Objects.equals(user, carrier.user) &&
                Objects.equals(title, carrier.title) &&
                Objects.equals(rating, carrier.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, title, rating);
    }
}
