package ru.project.compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.compass.entity.Stop;
import ru.project.compass.entity.Template;

import java.util.List;

public interface TemplateRepository extends JpaRepository<Template, Long> {


}
