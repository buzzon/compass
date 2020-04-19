package ru.naumen.compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumen.compass.entity.Ride;

public interface RideRepository extends JpaRepository<Ride, Long> {
}
