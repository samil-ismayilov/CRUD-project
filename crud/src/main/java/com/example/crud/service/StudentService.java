package com.example.crud.service;

import com.example.crud.dto.StudentDTO;
import com.example.crud.model.Course;
import com.example.crud.model.Student;
import com.example.crud.repository.CourseRepo;
import com.example.crud.repository.StudentRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentService {
    private final StudentRepo studentRepo;
    private final CourseRepo courseRepo;

    public Student createStudent(Student student) {
        return studentRepo.save(student);
    }

    public void deleteStudent(Long id) {
        Student student = studentRepo.findById(id).orElseThrow(() -> new RuntimeException("Telebe tapilmadi "));
        studentRepo.delete(student);
    }

    public Student addCourseToStudent(Long studentId, Long courseId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Tələbə tapılmadı! ID: " + studentId));

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Kurs tapılmadı! ID: " + courseId));

        student.setCourseId((long) course.getId());
        return studentRepo.save(student);
    }
    public List<StudentDTO> getAllStudentsWithCourses() {
        List<Student> students = studentRepo.findAll();

        return students.stream().map(student -> {
            Course course = null;
            if (student.getCourseId() != null) {
                course = courseRepo.findById(student.getCourseId()).orElse(null);
            }
            return new StudentDTO(
                    student.getId(),
                    student.getName(),
                    student.getEmail(),
                    course
            );
        }).collect(Collectors.toList());
    }
}
