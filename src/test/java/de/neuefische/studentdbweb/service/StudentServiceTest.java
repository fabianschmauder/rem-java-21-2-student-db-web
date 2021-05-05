package de.neuefische.studentdbweb.service;

import de.neuefische.studentdbweb.model.Student;
import de.neuefische.studentdbweb.repository.StudentRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;

class StudentServiceTest {

    private final StudentRepository studentRepository = new StudentRepository();
    private final StudentService service = new StudentService(studentRepository);

    @Test
    public void listShouldReturnAllStudents() {
        //GIVEN
        service.addStudent(new Student("1", "Frank"));
        service.addStudent(new Student("2", "Jan"));

        //WHEN
        List<Student> list = service.list();

        //THEN
        assertThat(list, containsInAnyOrder(new Student("1", "Frank"),
                new Student("2", "Jan")));
    }

    @Test
    public void findByIdShouldReturnStudentWithMatchingId() {
        //GIVEN
        service.addStudent(new Student("1", "Frank"));
        service.addStudent(new Student("2", "Jan"));
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
        service.addStudent(new Student("1", "Frank"));
        service.addStudent(new Student("2", "Jan"));
        String idToFind = "42";
        //WHEN
        Optional<Student> student = service.findById(idToFind);

        //THEN
        assertThat(student.isPresent(), is(false));
    }


    @Test
    public void findStudentByNameShouldReturnStudentWithNameContainingSearchString() {
        //GIVEN
        service.addStudent(new Student("1", "Frank"));
        service.addStudent(new Student("2", "Jan"));
        String searchString = "nk";

        //WHEN
        List<Student> students = service.filterStudentByName(searchString);

        //THEN
        assertThat(students, containsInAnyOrder(new Student("1", "Frank")));
    }


    @Test
    public void findStudentByNameShouldReturnStudentWithNameContainingSearchStringIgnoreCase() {
        //GIVEN

        service.addStudent(new Student("1", "Frank"));
        service.addStudent(new Student("2", "Jan"));
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
