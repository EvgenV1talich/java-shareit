package ru.practicum.shareit.exceptions.user;

public class UserHaveNoItemsException extends RuntimeException {
    public UserHaveNoItemsException(String message) {
        super(message);
    }
}
