package de.neuefische.studentdbweb.controller;

import de.neuefische.studentdbweb.model.Student;
import de.neuefische.studentdbweb.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    StudentRepository repository;

    @Autowired
    TestRestTemplate restTemplate;

    @BeforeEach
    public void clearDb() {
        repository.clear();
    }

    @Test
    public void getStudentShouldListAllStudents() {
        //GIVEN
        repository.addStudent(new Student("13", "Frank"));
        repository.addStudent(new Student("42", "Jan"));


        //WHEN
        ResponseEntity<Student[]> response = restTemplate.getForEntity("http://localhost:" + port + "/student", Student[].class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), arrayContainingInAnyOrder(
                new Student("13", "Frank"),
                new Student("42", "Jan")));
    }

    @Test
    public void getStudentByIdShouldReturnStudent() {
        //GIVEN
        repository.addStudent(new Student("13", "Frank"));
        repository.addStudent(new Student("42", "Jan"));


        //WHEN
        ResponseEntity<Student> response = restTemplate.getForEntity("http://localhost:" + port + "/student/42", Student.class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(new Student("42", "Jan")));
    }

    @Test
    public void getStudentByIdShouldReturn404WhenUserNotExists() {
        //GIVEN
        repository.addStudent(new Student("13", "Frank"));


        //WHEN
        ResponseEntity<Void> response = restTemplate.getForEntity("http://localhost:" + port + "/student/42", Void.class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

}
