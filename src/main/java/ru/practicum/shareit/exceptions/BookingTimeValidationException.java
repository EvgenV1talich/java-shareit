package ru.practicum.shareit.exceptions;

public class BookingTimeValidationException extends RuntimeException {
    public BookingTimeValidationException(String message) {
        super(message);
    }
}
