package com.shortmile.springexamples.restdb.entities;

import com.shortmile.springexamples.restdb.resources.UserResource;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String email;

    public static UserEntity fromUser(UserResource user) {
        return UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public UserResource toUser() {
        return UserResource.builder()
                .id(id)
                .name(name)
                .email(email)
                .build();
    }
}
