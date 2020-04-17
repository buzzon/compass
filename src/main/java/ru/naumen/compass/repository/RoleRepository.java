package ru.naumen.compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumen.compass.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByTitle(String title);
}
