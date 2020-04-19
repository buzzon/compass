package ru.naumen.compass.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "transport", cascade = CascadeType.ALL)
    private Set<Template> templates = new HashSet<>();

    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Template> getTemplates() {
        return templates;
    }

    public void setTemplates(Set<Template> templates) {
        this.templates = templates;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
