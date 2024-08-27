package ru.practicum.shareit.booking.service;

import ru.practicum.shareit.booking.dto.BookingDto;

import java.util.List;

public interface BookingService {

    BookingDto addRequest(BookingDto request, Long bookerId);

    BookingDto approveRequest(Long requestOwnerId, Long id, Boolean isApproved);

    BookingDto getInfoForRequest(Long id, Long requesterId);

    List<BookingDto> getAllBookingsByUser(Long id, String state);

    List<BookingDto> getAllBookingsByOwnerItems(Long id, String state);

}
