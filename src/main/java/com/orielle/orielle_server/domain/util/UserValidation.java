package com.orielle.orielle_server.domain.util;

import com.orielle.orielle_server.domain.exception.EmptyFieldException;
import com.orielle.orielle_server.domain.exception.InvalidFieldException;
import com.orielle.orielle_server.domain.exception.MaxLengthException;
import com.orielle.orielle_server.domain.model.User;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

public class UserValidation {
    public void validateUser(User user) {
        validateNameUser(user.getName());
        validateLastNameUser(user.getLastName());
        validateDocumentUser(user.getDni());
        validateEmailUser(user.getEmail());
        validatePhoneUser(user.getPhone());
        validatePasswordUser(user.getPassword());
        validateAgeUser(user.getBirthdate());
    }

    private void validateNameUser(String name) {
        if (name.trim().isEmpty()) {
            throw new EmptyFieldException(Constants.MINIMUM_USER_NAME_CHARACTERS_MESSAGE);
        }

        if (name.trim().length() > Constants.MAXIMUM_USER_NAME_CHARACTERS) {
            throw new MaxLengthException(Constants.MAXIMUM_USER_NAME_CHARACTERS_MESSAGE);
        }
    }

    private void validateLastNameUser(String lastName) {
        if (lastName.trim().isEmpty()) {
            throw new EmptyFieldException(Constants.MINIMUM_USER_LASTNAME_CHARACTERS_MESSAGE);
        }

        if (lastName.trim().length() > Constants.MAXIMUM_USER_LASTNAME_CHARACTERS) {
            throw new MaxLengthException(Constants.MAXIMUM_USER_LASTNAME_CHARACTERS_MESSAGE);
        }
    }

    private void validateDocumentUser(String document) {
        if (document.trim().isEmpty()) {
            throw new EmptyFieldException(Constants.MINIMUM_USER_DNI_CHARACTERS_MESSAGE);
        }

        if (!Pattern.matches(Constants.DNI_REGEX, document)) {
            throw new InvalidFieldException(Constants.INVALID_DOCUMENT);
        }
    }

    private void validateEmailUser(String email) {
        if (email.trim().isEmpty()) {
            throw new EmptyFieldException(Constants.MINIMUM_USER_EMAIL_CHARACTERS_MESSAGE);
        }

        if (!Pattern.matches(Constants.EMAIL_REGEX, email)) {
            throw new InvalidFieldException(Constants.INVALID_EMAIL);
        }
    }

    private void validatePhoneUser(String phone) {
        if (phone.trim().isEmpty()) {
            throw new EmptyFieldException(Constants.MINIMUM_USER_PHONE_CHARACTERS_MESSAGE);
        }

        if (phone.trim().length() > Constants.MAXIMUM_USER_PHONE_CHARACTERS) {
            throw new MaxLengthException(Constants.MAXIMUM_USER_PHONE_CHARACTERS_MESSAGE);
        }

        if (!Pattern.matches(Constants.PHONE_REGEX, phone)) {
            throw new InvalidFieldException(Constants.INVALID_PHONE);
        }
    }

    private void validatePasswordUser(String password) {
        if (password.trim().isEmpty()) {
            throw new EmptyFieldException(Constants.MINIMUM_USER_PASSWORD_CHARACTERS_MESSAGE);
        }

        if (!Pattern.matches(Constants.PASSWORD_REGEX, password)) {
            throw new InvalidFieldException(Constants.INVALID_PASSWORD);
        }
    }

    private void validateAgeUser(LocalDate birthdate) {
        if (Period.between(birthdate, LocalDate.now()).getYears() < Constants.MINIMUM_AGE_USER) {
            throw new InvalidFieldException(Constants.INVALID_AGE);
        }
    }
}
