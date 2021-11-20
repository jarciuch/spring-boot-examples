package com.shortmile.springexamples.restdb.controllers;

import com.shortmile.springexamples.restdb.entities.UserEntity;
import com.shortmile.springexamples.restdb.repositories.UserRepository;
import com.shortmile.springexamples.restdb.resources.UserResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class UserRestController {

    private final UserRepository userRepository;

    public UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    List<UserResource> getUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(UserEntity::toUser)
                .collect(Collectors.toList());
    }

}
