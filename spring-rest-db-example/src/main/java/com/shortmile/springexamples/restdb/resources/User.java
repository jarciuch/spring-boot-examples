package com.shortmile.springexamples.restdb.resources;

import lombok.*;

@Data
@Builder
public class User {
    private final Long id;
    private final String name;
    private final String email;
}
