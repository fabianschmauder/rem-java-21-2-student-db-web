package de.neuefische.studentdbweb.controller;

import de.neuefische.studentdbweb.model.Student;
import de.neuefische.studentdbweb.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService = new StudentService();

    @GetMapping
    public List<Student> listStudents(@RequestParam Optional<String> search) {
        return studentService.list();
    }

    @GetMapping("{id}")
    public Student getStudentFrank(@PathVariable String id) {
        Optional<Student> optionalStudent = studentService.findById(id);
        return optionalStudent.orElse(null);
    }
}
