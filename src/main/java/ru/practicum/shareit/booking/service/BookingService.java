package ru.practicum.shareit.booking.service;

import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.model.Booking;

import java.util.List;

public interface BookingService {

    BookingDto addRequest(Booking request);

    BookingDto approveRequest(Long requestOwnerId, Long id, Boolean isApproved);

    BookingDto getInfoForRequest(Long id, Long requesterId);

    List<BookingDto> getAllBookingsByUser(Long id, String state);

    List<BookingDto> getAllBookingsByOwnerItems(Long id);

}
