package de.neuefische.studentdbweb.controller;

import de.neuefische.studentdbweb.model.User;
import de.neuefische.studentdbweb.service.UserApiService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserApiService userApiService;

    public UserController(UserApiService userApiService) {
        this.userApiService = userApiService;
    }


    @GetMapping("{id}")
    public User getUserById(@PathVariable String id) {
        Optional<User> userOptional = userApiService.getUserById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user with id " + id + " not found");
    }

    @GetMapping
    public List<User> listUsers() {
        return userApiService.listUsers();
    }
}
