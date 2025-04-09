package com.orielle.orielle_server.domain.spi;

import com.orielle.orielle_server.domain.model.CustomPage;
import com.orielle.orielle_server.domain.model.User;

import java.util.Optional;

public interface IUserPersistencePort {
    User saveUser(User user);
    Optional<User> getUserByDocument(String name);
    Optional<User> getUserByEmail(String email);
    Optional<User> getUserByPhone(String phone);
    CustomPage<User> getAllClients(Integer page, Integer size, Boolean ascending, String sortBy);
}
