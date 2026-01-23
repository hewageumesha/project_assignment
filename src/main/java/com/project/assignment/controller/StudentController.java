package com.project.assignment.controller;

import com.project.assignment.model.Project;
import com.project.assignment.model.Student;
import com.project.assignment.repository.ProjectRepository;
import com.project.assignment.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "${SPRING_ORIGINS:*}")
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentRepository.findAll());
    }

    public Student retieveStudentById(int id) {
        return studentRepository.findById(id).orElseThrow(
                ()-> new ExpressionException("Student with id " + id + " is not found!")
        );
    }

    public Project retieveProjectById(int id) {
        return projectRepository.findById(id).orElseThrow(
                ()-> new ExpressionException("Project with id " + id + " is not found!")
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        return ResponseEntity.of(studentRepository.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Student> createStudent(@RequestBody Student studentDetails) {
        Student student = studentRepository.save(studentDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student studentDetails) {
        Student student = retieveStudentById(id);
        student.setName(studentDetails.getName());
        student.setAverage(studentDetails.getAverage());
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        Student student = retieveStudentById(id);
        studentRepository.delete(student);
        return ResponseEntity.ok("Student deleted successfully!");
    }
}
