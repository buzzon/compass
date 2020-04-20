package ru.project.compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.compass.entity.Discount;

public interface DiscountRepository extends JpaRepository<Discount, Long> {

}
