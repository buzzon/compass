package ru.project.compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.compass.entity.Direction;

public interface DirectionRepository extends JpaRepository<Direction, Long> {

}
