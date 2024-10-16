package ru.practicum.shareit.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * TODO Sprint add-bookings.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {

    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private Long itemId;
    private int bookerId;
    private BookingStatus status;

}
