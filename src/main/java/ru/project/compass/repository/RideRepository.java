package ru.project.compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.compass.entity.Ride;
import ru.project.compass.entity.Transport;

import java.util.Optional;

public interface RideRepository extends JpaRepository<Ride, Long> {
}
