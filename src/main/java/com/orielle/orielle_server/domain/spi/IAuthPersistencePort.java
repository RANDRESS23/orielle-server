package com.orielle.orielle_server.domain.spi;

import com.orielle.orielle_server.domain.model.User;

public interface IAuthPersistencePort {
    Long getAuthenticatedUserId();
    String getAuthenticatedUserEmail();
    boolean validateCredentials(String email, String password);
    User authenticate(String email, String password);
    String generateToken(User user);
}
