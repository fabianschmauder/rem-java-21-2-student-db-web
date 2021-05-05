package de.neuefische.studentdbweb.service;

import de.neuefische.studentdbweb.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final List<Student> students = new ArrayList<>();

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

    public Student addStudent(Student student){
        this.students.add(student);
        return student;
    }
}
