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
        if(search.isPresent() && !search.get().isBlank()){
            return studentService.filterStudentByName(search.get());
        }
        return studentService.list();
    }

    @GetMapping("{id}")
    public Student getStudentById(@PathVariable String id) {
        Optional<Student> optionalStudent = studentService.findById(id);
        return optionalStudent.orElse(null);
    }
}