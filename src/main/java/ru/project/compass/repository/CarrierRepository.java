package ru.project.compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.compass.entity.Carrier;

public interface CarrierRepository extends JpaRepository<Carrier, Long> {
}
