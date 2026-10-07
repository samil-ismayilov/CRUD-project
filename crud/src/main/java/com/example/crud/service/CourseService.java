package com.example.crud.service;

import com.example.crud.model.Course;
import com.example.crud.repository.CourseRepo;
import com.example.crud.repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepo courseRepo;
    private StudentRepo studentRepo;
    public Course createCourse(Course course) {
        return courseRepo.save(course);
    }
    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }
    public Course getCourseById(Long id) {
        return courseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Kurs tapılmadı "));
    }
    public Course updateCourse(Long id, Course courseDetails) {
        Course course = getCourseById(id);
        course.setName(courseDetails.getName());
        course.setDescription(courseDetails.getDescription());
        return courseRepo.save(course);
    }
    public void deleteCourse(Long id) {
        Course course = getCourseById(id);
        courseRepo.delete(course);
    }
}
