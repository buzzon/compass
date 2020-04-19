package ru.naumen.compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumen.compass.entity.Stop;

public interface StopRepository extends JpaRepository<Stop, Long> {

}
