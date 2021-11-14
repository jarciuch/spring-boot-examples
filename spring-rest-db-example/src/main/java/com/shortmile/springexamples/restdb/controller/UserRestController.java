package com.shortmile.springexamples.restdb.controller;

import com.shortmile.springexamples.restdb.resource.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRestController {
    List<User> users = List.of(new User(1L, "John Doe", "john.doe@email.com"));


    @GetMapping("/users")
    List<User> getUsers() {
        return users;
    }

}
