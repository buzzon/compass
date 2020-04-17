package ru.naumen.compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumen.compass.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
