package com.example.crud.Controller;

import com.example.crud.dto.StudentDTO;
import com.example.crud.model.Student;
import com.example.crud.repository.StudentRepo;
import com.example.crud.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        return ResponseEntity.ok(studentService.createStudent(student));
    }
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudentsWithCourses());

    }
    @PutMapping("/{studentId}/courses/{courseId}")
    public ResponseEntity<Student> addCourseToStudent(@PathVariable Long studentId, @PathVariable Long courseId){
        return ResponseEntity.ok(studentService.addCourseToStudent(studentId, courseId));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Tələbə uğurla silindi.");
    }
}
