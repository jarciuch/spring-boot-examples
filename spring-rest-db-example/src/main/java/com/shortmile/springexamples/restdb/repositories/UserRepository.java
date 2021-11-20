package com.shortmile.springexamples.restdb.repositories;

import com.shortmile.springexamples.restdb.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
