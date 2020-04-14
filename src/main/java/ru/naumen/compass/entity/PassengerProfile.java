package ru.naumen.compass.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class PassengerProfile {
    @Id
    private Long id;

    @OneToOne
    @MapsId
    private Passenger passenger;

    @NotNull
    @NotEmpty
    private String l_name;

    @NotNull
    @NotEmpty
    private String f_name;

    private String m_name;

    private Integer rating;

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
        PassengerProfile that = (PassengerProfile) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(passenger, that.passenger) &&
                Objects.equals(l_name, that.l_name) &&
                Objects.equals(f_name, that.f_name) &&
                Objects.equals(m_name, that.m_name) &&
                Objects.equals(rating, that.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passenger, l_name, f_name, m_name, rating);
    }
}
