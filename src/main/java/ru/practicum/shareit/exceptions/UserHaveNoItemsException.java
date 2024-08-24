package ru.practicum.shareit.exceptions;

public class UserHaveNoItemsException extends RuntimeException {
    public UserHaveNoItemsException(String message) {
        super(message);
    }
}
