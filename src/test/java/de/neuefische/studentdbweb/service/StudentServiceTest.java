package de.neuefische.studentdbweb.service;

import de.neuefische.studentdbweb.model.Student;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;

class StudentServiceTest {


    private final StudentService service = new StudentService(List.of(
            new Student("1", "Frank"),
            new Student("2", "Jan")
    ));

    @Test
    public void listShouldReturnAllStudents() {
        //WHEN
        List<Student> list = service.list();

        //THEN
        assertThat(list, containsInAnyOrder(new Student("1", "Frank"),
                new Student("2", "Jan")));
    }

    @Test
    public void findByIdShouldReturnStudentWithMatchingId() {
        //GIVEN
        String idToFind = "2";
        //WHEN
        Optional<Student> student = service.findById(idToFind);

        //THEN
        assertThat(student.isPresent(), is(true));
        assertThat(student.get(), is(new Student("2", "Jan")));
    }


    @Test
    public void findByIdShouldReturnEmptyOptionalWhenStudentWithIdNotExists() {
        //GIVEN
        String idToFind = "42";
        //WHEN
        Optional<Student> student = service.findById(idToFind);

        //THEN
        assertThat(student.isPresent(), is(false));
    }


    @Test
    public void findStudentByNameShouldReturnStudentWithNameContainingSearchString() {
        //GIVEN
        String searchString = "nk";

        //WHEN
        List<Student> students = service.filterStudentByName(searchString);

        //THEN
        assertThat(students, containsInAnyOrder(new Student("1", "Frank")));
    }


    @Test
    public void findStudentByNameShouldReturnStudentWithNameContainingSearchStringIgnoreCase() {
        //GIVEN
        String searchString = "ja";

        //WHEN
        List<Student> students = service.filterStudentByName(searchString);

        //THEN
        assertThat(students, containsInAnyOrder(new Student("2", "Jan")));
    }

    @Test
    public void addStudentShouldAddStudentToList() {
        //GIVEN
        Student jochen = new Student("42", "Jochen");

        //WHEN
        Student result = service.addStudent(jochen);

        //THEN
        Student expectedResult = new Student("42", "Jochen");

        Optional<Student> student = service.findById("42");
        assertThat(student.isPresent(), is(true));
        assertThat(student.get(), is(expectedResult));
        assertThat(result, is(expectedResult));
    }

}
