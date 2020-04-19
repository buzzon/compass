package ru.naumen.compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumen.compass.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}
