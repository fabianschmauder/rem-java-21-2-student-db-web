package de.neuefische.studentdbweb.controller;

import de.neuefische.studentdbweb.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private RestTemplate mockedTemplate;


    @Test
    public void getUserWithIdShouldReturnUserFromPlaceholderApi(){
        //GIVEN
        when(mockedTemplate.getForEntity("https://jsonplaceholder.typicode.com/users/1",User.class)).thenReturn(ResponseEntity.ok(new User("1", "Frank", "superfrank")));


        //WHEN
        ResponseEntity<User> response = restTemplate.getForEntity("http://localhost:" + port + "/user/1", User.class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(new User("1", "Frank", "superfrank")));

    }
}
