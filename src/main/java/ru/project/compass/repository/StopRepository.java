package ru.project.compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.compass.entity.Stop;

public interface StopRepository extends JpaRepository<Stop, Long> {

}