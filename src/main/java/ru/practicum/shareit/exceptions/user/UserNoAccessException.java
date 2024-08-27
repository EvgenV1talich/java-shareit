package ru.practicum.shareit.exceptions.user;

public class UserNoAccessException extends RuntimeException {
    public UserNoAccessException(String message) {
        super(message);
    }
}
