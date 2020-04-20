package ru.project.compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.compass.entity.Ride;

public interface RideRepository extends JpaRepository<Ride, Long> {
}
