package com.shortmile.springexamples.restdb.controllers;

import com.shortmile.springexamples.restdb.resources.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRestController {

    List<User> users = List.of(User.builder().id(1L).name("John").email("john@email").build());


    @GetMapping("/users")
    List<User> getUsers() {
        return users;
    }

}
