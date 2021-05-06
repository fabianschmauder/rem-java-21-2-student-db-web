package de.neuefische.studentdbweb.service;

import de.neuefische.studentdbweb.model.Student;
import de.neuefische.studentdbweb.repository.StudentRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    private final StudentRepository studentRepository = mock(StudentRepository.class);
    private final StudentService service = new StudentService(studentRepository);

    @Test
    public void listShouldReturnAllStudentsFromRepository() {
        //GIVEN
        when(studentRepository.list()).thenReturn(List.of(
                new Student("1", "Frank"),
                new Student("2", "Jan")
        ));

        //WHEN
        List<Student> list = service.list();

        //THEN
        assertThat(list, containsInAnyOrder(new Student("1", "Frank"),
                new Student("2", "Jan")));
    }

    @Test
    public void findByIdShouldCallAndReturnFindByIdOnRepository() {
        //GIVEN
        when(studentRepository.findById("2")).thenReturn(Optional.of(new Student("2", "Jan")));
        String idToFind = "2";

        //WHEN
        Optional<Student> student = service.findById(idToFind);

        //THEN
        assertThat(student.isPresent(), is(true));
        assertThat(student.get(), is(new Student("2", "Jan")));
    }

    @Test
    public void findStudentByNameShouldReturnStudentWithNameContainingSearchString() {
        //GIVEN
        when(studentRepository.list()).thenReturn(List.of(
                new Student("1", "Frank"),
                new Student("2", "Jan")
        ));
        String searchString = "nk";

        //WHEN
        List<Student> students = service.filterStudentByName(searchString);

        //THEN
        assertThat(students, containsInAnyOrder(new Student("1", "Frank")));
    }


    @Test
    public void findStudentByNameShouldReturnStudentWithNameContainingSearchStringIgnoreCase() {
        //GIVEN

        when(studentRepository.list()).thenReturn(List.of(
                new Student("1", "Frank"),
                new Student("2", "Jan")
        ));
        String searchString = "ja";

        //WHEN
        List<Student> students = service.filterStudentByName(searchString);

        //THEN
        assertThat(students, containsInAnyOrder(new Student("2", "Jan")));
    }

    @Test
    public void addStudentShouldAddStudentToRepository() {
        //GIVEN
        Student jochen = new Student("42", "Jochen");
        when(studentRepository.addStudent(new Student("42", "Jochen"))).thenReturn(jochen);

        //WHEN
        Student result = service.addStudent(jochen);

        //THEN
        Student expectedResult = new Student("42", "Jochen");
        assertThat(result, is(expectedResult));
        verify(studentRepository).addStudent(new Student("42", "Jochen"));
    }

}
