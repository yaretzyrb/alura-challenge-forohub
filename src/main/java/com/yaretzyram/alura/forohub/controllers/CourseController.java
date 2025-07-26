package com.yaretzyram.alura.forohub.controllers;

import com.yaretzyram.alura.forohub.domains.models.course.Course;
import com.yaretzyram.alura.forohub.domains.models.course.CourseInputDTO;
import com.yaretzyram.alura.forohub.domains.models.course.CourseOutputDTO;
import com.yaretzyram.alura.forohub.domains.models.course.CourseRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<CourseOutputDTO> registerCourse(@RequestBody @Valid CourseInputDTO courseData, UriComponentsBuilder uriComponentsBuilder){
        Course course = new Course(courseData);
        System.out.println(course);
        courseRepository.save(course);

        CourseOutputDTO createdCourse = new CourseOutputDTO(course.getId(), course.getName(), course.getCategory());
        URI url = uriComponentsBuilder.path("/course/{id}").buildAndExpand(createdCourse.id()).toUri();
        return ResponseEntity.created(url).body(createdCourse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseOutputDTO> getCourseById(@PathVariable Long id){
        Course course = courseRepository.getReferenceById(id);
        CourseOutputDTO foundCourse = new CourseOutputDTO(course.getId(), course.getName(), course.getCategory());
        return ResponseEntity.ok(foundCourse);
    }

    @GetMapping
    public ResponseEntity<List<CourseOutputDTO>> getUsers(){
        return ResponseEntity.ok(courseRepository.findByActiveTrue().stream().toList());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteCourse(@PathVariable Long id){
        Course existingCourse = courseRepository.getReferenceById(id);
        existingCourse.deactivateCourse();
        return ResponseEntity.ok("Course deactivated");
    }

}
