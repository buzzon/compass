package ru.project.compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.compass.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
