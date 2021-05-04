package de.neuefische.studentdbweb.service;

import de.neuefische.studentdbweb.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentService {

    private final List<Student> students;

    public StudentService(List<Student> students) {
        this.students = new ArrayList<>(students);
    }

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

    public List<Student> filterStudentByName(String searchString) {
        List<Student> filteredStudents = new ArrayList<>();

        for (Student student : students) {
            if(student.getName().toLowerCase().contains(searchString.toLowerCase())){
                filteredStudents.add(student);
            }
        }

        return filteredStudents;
    }
}
