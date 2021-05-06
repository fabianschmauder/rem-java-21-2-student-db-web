package de.neuefische.studentdbweb.service;

import de.neuefische.studentdbweb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserApiService {
    private final String jsonPlaceholderUsersUrl = "https://jsonplaceholder.typicode.com/users";

    private final RestTemplate restTemplate;

    @Autowired
    public UserApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<User> getUserById(String id) {
        String jsonPlaceholderUrl = jsonPlaceholderUsersUrl + "/" + id;

        ResponseEntity<User> response = restTemplate.getForEntity(jsonPlaceholderUrl, User.class);

        if (response.getBody() != null) {
            return Optional.of(response.getBody());
        }

        return Optional.empty();
    }

    public List<User> listUsers() {

        ResponseEntity<User[]> response = restTemplate.getForEntity(jsonPlaceholderUsersUrl, User[].class);

        if (response.getBody() != null) {
            return Arrays.asList(response.getBody());
        }
        return List.of();
    }
}
