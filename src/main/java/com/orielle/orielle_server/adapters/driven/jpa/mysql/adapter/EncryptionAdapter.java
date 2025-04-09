package com.orielle.orielle_server.adapters.driven.jpa.mysql.adapter;

import com.orielle.orielle_server.domain.spi.IEncryptionPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class EncryptionAdapter implements IEncryptionPersistencePort {
    private final PasswordEncoder passwordEncoder;

    @Override
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
