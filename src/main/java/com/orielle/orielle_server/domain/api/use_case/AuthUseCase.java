package com.orielle.orielle_server.domain.api.use_case;

import com.orielle.orielle_server.domain.api.IAuthServicePort;
import com.orielle.orielle_server.domain.exception.InvalidCredentials;
import com.orielle.orielle_server.domain.model.User;
import com.orielle.orielle_server.domain.spi.IAuthPersistencePort;
import com.orielle.orielle_server.domain.util.Constants;

public class AuthUseCase implements IAuthServicePort {
    private final IAuthPersistencePort authPersistencePort;

    public AuthUseCase(IAuthPersistencePort authPersistencePort) {
        this.authPersistencePort = authPersistencePort;
    }

    @Override
    public String login(String email, String password) {
        if (!authPersistencePort.validateCredentials(email, password)) {
            throw new InvalidCredentials(Constants.INVALID_CREDENTIALS_MESSAGE);
        }

        User user = authPersistencePort.authenticate(email, password);
        return authPersistencePort.generateToken(user);
    }
}
