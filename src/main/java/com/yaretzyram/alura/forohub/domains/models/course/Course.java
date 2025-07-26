package com.yaretzyram.alura.forohub.domains.models.course;

import com.yaretzyram.alura.forohub.domains.models.topic.Topic;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "category", nullable = false)
    private Category category;
    @Column(name = "active", nullable = false)
    private boolean active;

    public Course() {}

    public Course(CourseInputDTO courseInputDTO) {
        this.name = courseInputDTO.name();
        this.category = courseInputDTO.category();
        this.active = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", active=" + active +
                '}';
    }

    public void deactivateCourse() {
    }
}
