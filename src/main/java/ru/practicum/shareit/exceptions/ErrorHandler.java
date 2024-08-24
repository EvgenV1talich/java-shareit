package ru.practicum.shareit.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, String> handleUserNoAccessException(UserNoAccessException ex) {
        return Map.of("error", "Ошибка доступа к item");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> handleEmailDuplicateException(EmailExistsException ex) {
        return Map.of("error", "Пользователь с таким e-mail уже существует");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleNoEmailException(FailInputParamsException ex) {
        return Map.of("error", "Ошибка обработки полученных данных (не заполнены/заполнены с ошибками обязательные поля)");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleItemNotFoundException(ItemNotFoundException ex) {
        return Map.of("error", "Ошибка обработки полученных данных (не заполнены/заполнены с ошибками обязательные поля)");
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleUserNotFoundException(UserNotFoundException ex) {
        return Map.of("error", "Такой пользователь не найден!");
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleItemNotAvailableException(ItemNotAvailableException ex) {
        return Map.of("error", "Эта вещь недоступна для бронирования");
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleBookingTimeValidationException(BookingTimeValidationException ex) {
        return Map.of("error", "Верните маховик времени Альбусу Дамблдору...");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleUserHaveNoItemsException(UserHaveNoItemsException ex) {
        return Map.of("error", "У пользователя нет вещей во владении...");
    }
}
