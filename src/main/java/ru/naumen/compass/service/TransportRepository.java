package ru.naumen.compass.service;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumen.compass.entity.Vehicle;

public interface TransportRepository extends JpaRepository<Vehicle, Long> {
}
