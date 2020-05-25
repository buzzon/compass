package ru.project.compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.compass.entity.Transport;

import java.util.Optional;

public interface TransportRepository extends JpaRepository<Transport, Long> {

}
