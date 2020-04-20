package ru.project.compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.compass.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByTitle(String title);
}
