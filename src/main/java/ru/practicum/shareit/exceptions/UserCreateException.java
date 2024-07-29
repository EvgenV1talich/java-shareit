package ru.practicum.shareit.exceptions;

public class UserCreateException extends RuntimeException {
    public UserCreateException(String message) {
        super(message);
    }
}
