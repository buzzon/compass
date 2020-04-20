package ru.project.compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.compass.entity.Ticketstatus;

public interface TicketstatusRepository extends JpaRepository<Ticketstatus, Long> {
    Ticketstatus findByTitle(String title);
}
