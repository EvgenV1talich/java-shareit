package ru.practicum.shareit.exceptions;

public class UserNoAcсessException extends RuntimeException {
    public UserNoAcсessException(String message) {
        super(message);
    }
}
