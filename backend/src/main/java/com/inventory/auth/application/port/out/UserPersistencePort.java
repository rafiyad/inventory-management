package com.inventory.auth.application.port.out;

import com.inventory.auth.domain.User;

import java.util.Optional;

/**
 * Outbound port — defines the interface for database operations.
 * The core domain logic depends on this port, not on concrete persistence implementations (DIP).
 */
public interface UserPersistencePort {

    boolean existsByEmail(String email);

    long count();

    User save(User user);

    Optional<User> findByEmail(String email);
}
