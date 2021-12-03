package com.shortmile.springexamples.restdb.resources;

import lombok.*;

@Data
@Builder
public class UserResource {
    private final Long id;
    private final String name;
    private final String email;
}
