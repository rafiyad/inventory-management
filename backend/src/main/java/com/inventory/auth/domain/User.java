package com.inventory.auth.domain;

import lombok.*;

import java.util.UUID;

/**
 * Pure domain object — no JPA annotations, no Spring dependencies.
 * Represents the business concept of a User in the system.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private boolean enabled;
}
