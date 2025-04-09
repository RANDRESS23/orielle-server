package com.orielle.orielle_server.domain.spi;

public interface IEncryptionPersistencePort {
    String encodePassword(String password);
}
