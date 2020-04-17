package ru.naumen.compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumen.compass.entity.Discount;

public interface DiscountRepository extends JpaRepository<Discount, Long> {

}
