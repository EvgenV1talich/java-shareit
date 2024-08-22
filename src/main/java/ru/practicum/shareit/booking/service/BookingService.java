package ru.practicum.shareit.booking.service;

import ru.practicum.shareit.booking.dto.BookingDto;

public interface BookingService {

    BookingDto create(Long requesterId);
    BookingDto read(Long id);
    BookingDto update(Long id);
    void delete(Long id);

}
