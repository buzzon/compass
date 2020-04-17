package ru.naumen.compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumen.compass.entity.Carrier;

public interface CarrierRepository extends JpaRepository<Carrier, Long> {
}
