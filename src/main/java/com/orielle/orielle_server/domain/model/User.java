package com.orielle.orielle_server.domain.model;

import com.orielle.orielle_server.domain.util.Constants;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;

public class User {
    private final Long userId;
    private final String name;
    private final String lastName;
    private final String dni;
    private final String email;
    private final String phone;
    private LocalDate birthdate;
    private String password;
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public User(UserBuilder builder) {
        this.userId = builder.userId;
        this.name = requireNonNull(builder.name, Constants.FIELD_NAME_NULL_MESSAGE);
        this.lastName = requireNonNull(builder.lastName, Constants.FIELD_LASTNAME_NULL_MESSAGE);
        this.dni = requireNonNull(builder.dni, Constants.FIELD_DOCUMENT_NULL_MESSAGE);
        this.phone = requireNonNull(builder.phone, Constants.FIELD_PHONE_NULL_MESSAGE);
        this.birthdate = requireNonNull(builder.birthdate, Constants.FIELD_BIRTHDATE_NULL_MESSAGE);
        this.email = requireNonNull(builder.email, Constants.FIELD_EMAIL_NULL_MESSAGE);
        this.password = requireNonNull(builder.password, Constants.FIELD_PASSWORD_NULL_MESSAGE);
        this.role = builder.role;
        this.createdAt = requireNonNull(builder.createdAt, Constants.FIELD_CREATED_AT_NULL_MESSAGE);
        this.updatedAt = requireNonNull(builder.updatedAt, Constants.FIELD_UPDATED_AT_NULL_MESSAGE);
    }

    public static class UserBuilder {
        private Long userId;
        private String name;
        private String lastName;
        private String dni;
        private String phone;
        private LocalDate birthdate;
        private String email;
        private String password;
        private Role role;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public UserBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder dni(String dni) {
            this.dni = dni;
            return this;
        }

        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder birthdate(LocalDate birthdate) {
            this.birthdate = birthdate;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder role(Role role) {
            this.role = role;
            return this;
        }

        public UserBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UserBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public Long getUserId() { return userId; }

    public String getName() { return name; }

    public String getLastName() { return lastName; }

    public String getDni() { return dni; }

    public String getPhone() { return phone; }

    public LocalDate getBirthdate() { return birthdate; }

    public String getEmail() { return email; }

    public String getPassword() { return password; }

    public Role getRole() { return role; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void setBirthdate(LocalDate birthdate) { this.birthdate = birthdate; }

    public void setPassword(String password) { this.password = password; }

    public void setRole(Role role) { this.role = role; }

    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
