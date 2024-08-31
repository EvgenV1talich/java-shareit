package ru.practicum.shareit.exceptions.booking;

public class BookingTimeValidationException extends RuntimeException {
    public BookingTimeValidationException(String message) {
        super(message);
    }
}
