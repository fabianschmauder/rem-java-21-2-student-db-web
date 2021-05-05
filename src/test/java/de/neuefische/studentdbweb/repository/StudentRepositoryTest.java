package de.neuefische.studentdbweb.repository;

import de.neuefische.studentdbweb.model.Student;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;

class StudentRepositoryTest {
    private final StudentRepository studentRepository = new StudentRepository();

    @Test
    public void listShouldReturnAllStudents() {
        //GIVEN
        studentRepository.addStudent(new Student("1", "Frank"));
        studentRepository.addStudent(new Student("2", "Jan"));

        //WHEN
        List<Student> list = studentRepository.list();

        //THEN
        assertThat(list, containsInAnyOrder(new Student("1", "Frank"),
                new Student("2", "Jan")));
    }

    @Test
    public void findByIdShouldReturnStudentWithMatchingId() {
        //GIVEN
        studentRepository.addStudent(new Student("1", "Frank"));
        studentRepository.addStudent(new Student("2", "Jan"));
        String idToFind = "2";

        //WHEN
        Optional<Student> student = studentRepository.findById(idToFind);

        //THEN
        assertThat(student.isPresent(), is(true));
        assertThat(student.get(), is(new Student("2", "Jan")));
    }


    @Test
    public void findByIdShouldReturnEmptyOptionalWhenStudentWithIdNotExists() {
        //GIVEN
        studentRepository.addStudent(new Student("1", "Frank"));
        studentRepository.addStudent(new Student("2", "Jan"));
        String idToFind = "42";
        //WHEN
        Optional<Student> student = studentRepository.findById(idToFind);

        //THEN
        assertThat(student.isPresent(), is(false));
    }


    @Test
    public void addStudentShouldAddStudentToList() {
        //GIVEN
        Student jochen = new Student("42", "Jochen");

        //WHEN
        Student result = studentRepository.addStudent(jochen);

        //THEN
        Student expectedResult = new Student("42", "Jochen");

        Optional<Student> student = studentRepository.findById("42");
        assertThat(student.isPresent(), is(true));
        assertThat(student.get(), is(expectedResult));
        assertThat(result, is(expectedResult));
    }

}
