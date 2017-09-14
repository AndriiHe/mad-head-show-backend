package com.incamp.mhs.user;

import javax.persistence.PersistenceException;

public class UserAlreadyExistsException extends PersistenceException {

    public UserAlreadyExistsException() {
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
