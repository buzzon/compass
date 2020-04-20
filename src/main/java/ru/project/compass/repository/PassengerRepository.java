package ru.project.compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.compass.entity.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
