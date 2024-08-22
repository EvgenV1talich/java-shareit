package ru.practicum.shareit.booking.service;

import lombok.RequiredArgsConstructor;
import ru.practicum.shareit.booking.dao.BookingDao;
import ru.practicum.shareit.booking.dto.BookingDto;

@RequiredArgsConstructor
public class BookingServicePSQLImpl implements BookingService {
    private final BookingDao dao;
    @Override
    public BookingDto create(Long requesterId) {
        return null;
    }

    @Override
    public BookingDto read(Long id) {
        return null;
    }

    @Override
    public BookingDto update(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
