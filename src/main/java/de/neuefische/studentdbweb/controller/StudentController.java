package de.neuefische.studentdbweb.controller;

import de.neuefische.studentdbweb.model.Student;
import de.neuefische.studentdbweb.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService = new StudentService(List.of(
            new Student("1", "Frank"),
            new Student("2", "Jan")
    ));

    @GetMapping
    public List<Student> listStudents(@RequestParam Optional<String> search) {
        if (search.isPresent() && !search.get().isBlank()) {
            return studentService.filterStudentByName(search.get());
        }
        return studentService.list();
    }

    @GetMapping("{id}")
    public Student getStudentById(@PathVariable String id) {
        Optional<Student> optionalStudent = studentService.findById(id);
        return optionalStudent.orElse(null);
    }

    @PutMapping("{id}")
    public Student addStudent(@PathVariable String id,
                              @Valid @RequestBody Student student) {
        if (!id.equals(student.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id not matching");
        }

        return studentService.addStudent(student);
    }

}
