package de.neuefische.studentdbweb.controller;

import de.neuefische.studentdbweb.model.Student;
import de.neuefische.studentdbweb.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService = new StudentService();

    @GetMapping
    public List<Student> listStudents() {
        return studentService.list();
    }

    @GetMapping("{id}")
    public Student getStudentFrank(@PathVariable String id) {
        Optional<Student> optionalStudent = studentService.findById(id);
        return optionalStudent.orElse(null);
    }
}
