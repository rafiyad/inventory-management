package com.inventory.auth.adapter.out.persistence;

import com.inventory.auth.adapter.out.persistence.entity.UserEntity;
import com.inventory.auth.adapter.out.persistence.repository.UserJpaRepository;
import com.inventory.auth.application.port.out.UserPersistencePort;
import com.inventory.auth.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Outbound persistence adapter — implements both the application port (UserPersistencePort)
 * and the Spring Security interface (UserDetailsService).
 * Bridges the domain layer with concrete database/framework mechanisms.
 */
@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserPersistencePort, UserDetailsService {

    private final UserJpaRepository userJpaRepository;

    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }

    @Override
    public long count() {
        return userJpaRepository.count();
    }

    @Override
    public User save(User user) {
        UserEntity entity = UserEntity.fromDomain(user);
        UserEntity savedEntity = userJpaRepository.save(entity);
        return savedEntity.toDomain();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email)
                .map(UserEntity::toDomain);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userJpaRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
    }
}
