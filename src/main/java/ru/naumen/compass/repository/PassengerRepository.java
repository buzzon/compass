package ru.naumen.compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumen.compass.entity.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
