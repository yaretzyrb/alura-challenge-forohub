package com.yaretzyram.alura.forohub.domains.models.course;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<CourseOutputDTO> findByActiveTrue();
}
