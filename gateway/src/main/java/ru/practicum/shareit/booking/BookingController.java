package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingState;

/**
 * TODO Sprint add-bookings.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/bookings")
public class BookingController {

    private final BookingClient client;

    @PostMapping
    public ResponseEntity<Object> addRequest(@RequestHeader(value = "X-Sharer-User-Id") Long requesterId,
                                             @RequestBody BookingDto booking) {
        return client.create(booking, requesterId);
    }

    @PatchMapping("/{bookingId}")
    public ResponseEntity<Object> approveRequest(@RequestHeader(value = "X-Sharer-User-Id") Long requesterId,
                                                 @PathVariable(value = "bookingId") Long bookingId,
                                                 @RequestParam Boolean approved) {
        return client.setApproved(requesterId.intValue(), bookingId.intValue(), approved);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<Object> getBookingInfo(@RequestHeader(value = "X-Sharer-User-Id") Long requesterId,
                                                 @PathVariable(value = "bookingId") Long bookingId) {
        return client.get(bookingId, requesterId);
    }

    @GetMapping
    public ResponseEntity<Object> getBookingsForUser(@RequestHeader(value = "X-Sharer-User-Id") Long requesterId,
                                                     @RequestParam(value = "state", required = false, defaultValue = "ALL") BookingState state) {
        return client.getBookings(requesterId, state);
    }

    @GetMapping("/owner")
    public ResponseEntity<Object> getBookingsForItemsByUser(@RequestHeader(value = "X-Sharer-User-Id") Long requesterId,
                                                            @RequestParam(value = "state", required = false, defaultValue = "ALL") BookingState state) {
        return client.getAllByOwner(requesterId, state);
    }
}
