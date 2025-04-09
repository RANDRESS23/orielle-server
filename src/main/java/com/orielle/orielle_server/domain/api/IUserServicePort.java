package com.orielle.orielle_server.domain.api;

import com.orielle.orielle_server.domain.model.CustomPage;
import com.orielle.orielle_server.domain.model.User;

public interface IUserServicePort {
    String validateUser(User user);
    User saveClient(User user);
    CustomPage<User> getAllClients(Integer page, Integer size, Boolean ascending, String sortBy);
}
