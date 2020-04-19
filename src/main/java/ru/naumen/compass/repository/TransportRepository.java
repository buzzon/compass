package ru.naumen.compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumen.compass.entity.Transport;

public interface TransportRepository extends JpaRepository<Transport, Long> {

}
