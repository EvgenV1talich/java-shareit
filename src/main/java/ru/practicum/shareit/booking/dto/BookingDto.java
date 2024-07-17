package ru.practicum.shareit.booking.dto;

import lombok.RequiredArgsConstructor;
import ru.practicum.shareit.booking.Booking;
import ru.practicum.shareit.booking.BookingStatus;
import ru.practicum.shareit.item.model.Item;

import java.time.LocalDateTime;

/**
 * TODO Sprint add-bookings.
 */
@RequiredArgsConstructor
public class BookingDto {


    private final LocalDateTime start;
    private final LocalDateTime end;
    private final Item item;
    private final BookingStatus status;

    public static BookingDto toBookingDto(Booking booking) {
        return new BookingDto(
                booking.getStart(),
                booking.getEnd(),
                booking.getItem(),
                booking.getStatus()
        );
    }

}
