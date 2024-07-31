package ru.practicum.shareit.exceptions;

public class UserNoAccessException extends RuntimeException {
    public UserNoAccessException(String message) {
        super(message);
    }
}
