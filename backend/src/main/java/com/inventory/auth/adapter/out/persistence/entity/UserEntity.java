package com.inventory.auth.adapter.out.persistence.entity;

import com.inventory.auth.domain.Role;
import com.inventory.auth.domain.User;
import com.inventory.core.base.adapter.out.persistence.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity extends BaseEntity implements UserDetails {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(name = "is_enabled", nullable = false)
    @Builder.Default
    private boolean enabled = true;

    // Convert JPA Entity to Domain Model
    public User toDomain() {
        return User.builder()
                .id(this.getId())
                .firstName(this.firstName)
                .lastName(this.lastName)
                .email(this.email)
                .password(this.password)
                .role(this.role)
                .enabled(this.enabled)
                .build();
    }

    // Create JPA Entity from Domain Model
    public static UserEntity fromDomain(User domainUser) {
        UserEntity entity = UserEntity.builder()
                .firstName(domainUser.getFirstName())
                .lastName(domainUser.getLastName())
                .email(domainUser.getEmail())
                .password(domainUser.getPassword())
                .role(domainUser.getRole())
                .enabled(domainUser.isEnabled())
                .build();
        entity.setId(domainUser.getId());
        return entity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
