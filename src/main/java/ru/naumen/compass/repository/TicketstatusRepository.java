package ru.naumen.compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumen.compass.entity.Ticketstatus;

public interface TicketstatusRepository extends JpaRepository<Ticketstatus, Long> {
    Ticketstatus findByTitle(String title);
}
