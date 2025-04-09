package com.orielle.orielle_server.adapters.driven.jpa.mysql.entity;

import com.orielle.orielle_server.adapters.driven.jpa.mysql.util.DrivenConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = DrivenConstants.USERS_TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DrivenConstants.COLUMN_USER_ID)
    private Long userId;

    @Column(name = DrivenConstants.COLUMN_NAME, nullable = false, length = DrivenConstants.MAXIMUM_USER_NAME_CHARACTERS)
    private String name;

    @Column(name = DrivenConstants.COLUMN_USER_LASTNAME, nullable = false, length = DrivenConstants.MAXIMUM_USER_LASTNAME_CHARACTERS)
    private String lastName;

    @Column(name = DrivenConstants.COLUMN_USER_DNI, nullable = false, unique = true)
    @Pattern(regexp = DrivenConstants.DOCUMENT_REGEX, message = DrivenConstants.INVALID_DNI)
    private String dni;

    @Column(name = DrivenConstants.COLUMN_USER_PHONE, nullable = false, unique = true, length = DrivenConstants.MAXIMUM_USER_PHONE_CHARACTERS)
    @Pattern(regexp = DrivenConstants.PHONE_REGEX, message = DrivenConstants.INVALID_PHONE)
    private String phone;

    @Column(name = DrivenConstants.COLUMN_USER_BIRTHDATE, nullable = false)
    private LocalDate birthdate;

    @Column(name = DrivenConstants.COLUMN_USER_EMAIL, nullable = false, unique = true)
    @Pattern(regexp = DrivenConstants.EMAIL_REGEX, message = DrivenConstants.INVALID_EMAIL)
    private String email;

    @Column(name = DrivenConstants.COLUMN_USER_PASSWORD, nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = DrivenConstants.COLUMN_ROLE_ID)
    private RoleEntity role;

    @Column(name = DrivenConstants.COLUMN_CART_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    @Column(name = DrivenConstants.COLUMN_CART_UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getName().name()));
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

    @Override
    public boolean isEnabled() {
        return true;
    }
}
