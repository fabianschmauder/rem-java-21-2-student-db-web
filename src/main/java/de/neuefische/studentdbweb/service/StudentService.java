package de.neuefische.studentdbweb.service;

import de.neuefische.studentdbweb.model.Student;

import java.util.List;
import java.util.Optional;

public class StudentService {

    private final List<Student> students = List.of(
            new Student("1", "Frank"),
            new Student("2", "Jan")
    );

    public List<Student> list() {
        return students;
    }

    public Optional<Student> findById(String id) {
        for (Student student : students) {
            if(student.getId().equals(id)){
                return Optional.of(student);
            }
        }
        return Optional.empty();
    }
}
