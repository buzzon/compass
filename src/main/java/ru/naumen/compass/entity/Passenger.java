package ru.naumen.compass.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "passenger")
    private User user;

    private String l_name;
    private String f_name;
    private String m_name;
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
        Passenger passenger = (Passenger) o;
        return Objects.equals(id, passenger.id) &&
                Objects.equals(user, passenger.user) &&
                Objects.equals(l_name, passenger.l_name) &&
                Objects.equals(f_name, passenger.f_name) &&
                Objects.equals(m_name, passenger.m_name) &&
                Objects.equals(rating, passenger.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, l_name, f_name, m_name, rating);
    }
}
