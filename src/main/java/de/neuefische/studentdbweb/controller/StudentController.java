package de.neuefische.studentdbweb.controller;

import de.neuefische.studentdbweb.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {


    @GetMapping
    public List<Student> listStudents() {
        return List.of(
                new Student("1", "Frank"),
                new Student("2", "Jan")

        );
    }


    @GetMapping("1")
    public Student getStudentFrank() {
        return new Student("1", "Frank");
    }
}
