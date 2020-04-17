package ru.naumen.compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumen.compass.entity.Template;

public interface TemplateRepository extends JpaRepository<Template, Long> {

}
