package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
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
import ru.practicum.shareit.booking.service.BookingService;

import java.util.List;

/**
 * TODO Sprint add-bookings.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/bookings")
public class BookingController {

    private final BookingService service;

    @PostMapping
    public BookingDto addRequest(@RequestHeader(value = "X-Sharer-User-Id") Long requesterId,
                                 @RequestBody BookingDto booking) {
        return service.addRequest(booking, requesterId);
    }

    @PatchMapping("/{bookingId}")
    public BookingDto approveRequest(@RequestHeader(value = "X-Sharer-User-Id") Long requesterId,
                                     @PathVariable(value = "bookingId") Long bookingId,
                                     @RequestParam Boolean approved) {
        return service.approveRequest(requesterId, bookingId, approved);
    }

    @GetMapping("/{bookingId}")
    public BookingDto getBookingInfo(@RequestHeader(value = "X-Sharer-User-Id") Long requesterId,
                                     @PathVariable(value = "bookingId") Long bookingId) {
        return service.getInfoForRequest(bookingId, requesterId);
    }

    @GetMapping
    public List<BookingDto> getBookingsForUser(@RequestHeader(value = "X-Sharer-User-Id") Long requesterId,
                                               @RequestParam(value = "state", required = false) String state) {
        return service.getAllBookingsByUser(requesterId, state);
    }

    @GetMapping("/owner")
    public List<BookingDto> getBookingsForItemsByUser(@RequestHeader(value = "X-Sharer-User-Id") Long requesterId,
                                                      @RequestParam(value = "state", required = false) String state) {
        return service.getAllBookingsByOwnerItems(requesterId, state);
    }
}
