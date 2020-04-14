package ru.naumen.compass.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Passenger {
    @Id
    private Long id;

    @OneToOne(mappedBy = "passenger")
    private PassengerProfile passengerProfile;

    @OneToOne(mappedBy = "passenger")
    private Ticket ticket;

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String password;

    public Passenger() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return Objects.equals(id, passenger.id) &&
                Objects.equals(username, passenger.username) &&
                Objects.equals(password, passenger.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }
}
