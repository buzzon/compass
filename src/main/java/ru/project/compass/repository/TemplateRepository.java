package ru.project.compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.compass.entity.Template;

public interface TemplateRepository extends JpaRepository<Template, Long> {

}
