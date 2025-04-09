package com.orielle.orielle_server.domain.api.use_case;

import com.orielle.orielle_server.domain.api.IRoleServicePort;
import com.orielle.orielle_server.domain.api.IUserServicePort;
import com.orielle.orielle_server.domain.enums.RoleEnum;
import com.orielle.orielle_server.domain.exception.AlreadyExistsFieldException;
import com.orielle.orielle_server.domain.exception.InvalidSortByParamException;
import com.orielle.orielle_server.domain.model.CustomPage;
import com.orielle.orielle_server.domain.model.Role;
import com.orielle.orielle_server.domain.model.User;
import com.orielle.orielle_server.domain.spi.IEncryptionPersistencePort;
import com.orielle.orielle_server.domain.spi.IUserPersistencePort;
import com.orielle.orielle_server.domain.util.Constants;
import com.orielle.orielle_server.domain.util.UserValidation;

import java.util.Arrays;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;
    private final IRoleServicePort roleServicePort;
    private final IEncryptionPersistencePort encryptionPersistencePort;
    private final UserValidation userValidation;

    public UserUseCase(IUserPersistencePort userPersistencePort, IRoleServicePort roleServicePort, IEncryptionPersistencePort encryptionPersistencePort, UserValidation userValidation) {
        this.userPersistencePort = userPersistencePort;
        this.roleServicePort = roleServicePort;
        this.encryptionPersistencePort = encryptionPersistencePort;
        this.userValidation = userValidation;
    }

    @Override
    public String validateUser(User user) {
        if (userPersistencePort.getUserByDocument(user.getDni()).isPresent()) {
            throw new AlreadyExistsFieldException(Constants.DOCUMENT_ALREADY_EXISTS_MESSAGE);
        }

        if (userPersistencePort.getUserByEmail(user.getEmail()).isPresent()) {
            throw new AlreadyExistsFieldException(Constants.EMAIL_ALREADY_EXISTS_MESSAGE);
        }

        if (userPersistencePort.getUserByPhone(user.getPhone()).isPresent()) {
            throw new AlreadyExistsFieldException(Constants.PHONE_ALREADY_EXISTS_MESSAGE);
        }

        userValidation.validateUser(user);

        return encryptionPersistencePort.encodePassword(user.getPassword());
    }

    @Override
    public User saveClient(User user) {
        String encryptedPassword = validateUser(user);
        Role role = roleServicePort.getRole(RoleEnum.CLIENTE);
        user.setRole(role);
        user.setPassword(encryptedPassword);

        return userPersistencePort.saveUser(user);
    }

    @Override
    public CustomPage<User> getAllClients(Integer page, Integer size, Boolean ascending, String sortBy) {
        String[] sortByParams = {Constants.FIELD_NAME, Constants.FIELD_DNI, Constants.FIELD_EMAIL};

        if (Arrays.stream(sortByParams)
                .noneMatch(param -> param.equalsIgnoreCase(sortBy))) {
            throw new InvalidSortByParamException(Constants.INVALID_PARAM_MESSAGE);
        }

        return userPersistencePort.getAllClients(page, size, ascending, sortBy);
    }
}
