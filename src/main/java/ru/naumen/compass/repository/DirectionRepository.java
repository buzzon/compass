package ru.naumen.compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumen.compass.entity.Direction;

public interface DirectionRepository extends JpaRepository<Direction, Long> {

}
