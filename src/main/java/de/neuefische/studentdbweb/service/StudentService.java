package de.neuefische.studentdbweb.service;

import de.neuefische.studentdbweb.model.Student;
import de.neuefische.studentdbweb.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {


    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> list() {
        return studentRepository.list();
    }

    public Optional<Student> findById(String id) {
       return studentRepository.findById(id);
    }

    public List<Student> filterStudentByName(String searchString) {
        List<Student> filteredStudents = new ArrayList<>();

        for (Student student : studentRepository.list()) {
            if(student.getName().toLowerCase().contains(searchString.toLowerCase())){
                filteredStudents.add(student);
            }
        }

        return filteredStudents;
    }

    public Student addStudent(Student student){
        return this.studentRepository.addStudent(student);
    }
}
