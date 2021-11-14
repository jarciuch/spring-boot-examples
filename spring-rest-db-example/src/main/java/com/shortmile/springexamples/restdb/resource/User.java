package com.shortmile.springexamples.restdb.resource;

import lombok.*;

@Data
public class User {
    private final Long id;
    private final String name;
    private final String email;
}
