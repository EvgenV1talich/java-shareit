package ru.practicum.shareit.booking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.booking.dao.BookingDao;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.mapper.BookingMapper;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.BookingStatus;
import ru.practicum.shareit.exceptions.BookingTimeValidationException;
import ru.practicum.shareit.exceptions.FailInputParamsException;
import ru.practicum.shareit.exceptions.ItemNotAvailableException;
import ru.practicum.shareit.exceptions.UserHaveNoItemsException;
import ru.practicum.shareit.exceptions.UserNoAccessException;
import ru.practicum.shareit.item.mappers.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.service.UserService;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServicePSQLImpl implements BookingService {
    private final BookingDao bookingDao;
    private final UserService userService;
    private final ItemService itemService;
    private final BookingMapper bookingMapper;

    @Override
    public BookingDto addRequest(BookingDto request, Long bookerId) {
        Item item = ItemMapper.toItem(itemService.read(request.getItemId()));
        User booker = userService.read(bookerId);
        validateBookingTime(request);
        if (!item.getAvailable()) {
            throw new ItemNotAvailableException("Ошибка при запросе на бронирование недоступной вещи!");
        }
        //New booking creation
        Booking newBooking = new Booking();
        newBooking.setStatus(BookingStatus.WAITING);
        newBooking.setItem(item);
        newBooking.setStart(request.getStart());
        newBooking.setEnd(request.getEnd());
        newBooking.setBooker(booker);
        return bookingMapper.toDto(bookingDao.save(newBooking));
    }

    @Override
    public BookingDto approveRequest(Long requestOwnerId, Long bookingId, Boolean isApproved) {
        if (!requestOwnerId.equals(bookingDao
                .getReferenceById(bookingId)
                .getItem()
                .getOwner()
                .getId())) {
            throw new UserNoAccessException("Ошибка при подтверждении запроса бронирования (нет доступа)...");
        }
        Booking updatedBooking = bookingDao.getReferenceById(bookingId);
        if (isApproved) {
            updatedBooking.setStatus(BookingStatus.APPROVED);
        } else {
            updatedBooking.setStatus(BookingStatus.REJECTED);
        }
        return bookingMapper.toDto(bookingDao.save(updatedBooking));
    }

    @Override
    public BookingDto getInfoForRequest(Long id, Long requesterId) {
        if (requesterId.equals(bookingDao.getReferenceById(id).getItem().getOwner().getId()) ||
                requesterId.equals(bookingDao.getReferenceById(id).getBooker().getId())) {
            return bookingMapper.toDto(bookingDao.getReferenceById(id));
        } else {
            throw new UserNoAccessException("Ошибка при получении инфо о бронировании (нет доступа)...");
        }
    }

    @Override
    public List<BookingDto> getAllBookingsByUser(Long id, String state) {
        if (state == null) {
            return castToDtosAndSortByDate(bookingDao.findByBooker_Id(id));
        } else {
            return switch (state) {
                case "CURRENT" -> castToDtosAndSortByDate(bookingDao.findCurrentByUser(id, LocalDateTime.now()));
                case "PAST" -> castToDtosAndSortByDate(bookingDao.findPastByUser(id, LocalDateTime.now()));
                case "WAITING" -> castToDtosAndSortByDate(bookingDao.findWaitingByUser(id));
                case "REJECTED" -> castToDtosAndSortByDate(bookingDao.findRejectedByUser(id));
                default -> castToDtosAndSortByDate(bookingDao.findByBooker_Id(id));
            };
        }
    }

    private List<BookingDto> castToDtosAndSortByDate(List<Booking> bookings) {
        return bookings.stream()
                .sorted(Comparator.comparing(Booking::getEnd))
                .map(bookingMapper::toDto)
                .collect(Collectors.toList());
    }

    private void validateBookingTime(BookingDto booking) {
        if (booking.getStart() == null || booking.getEnd() == null) {
            throw new FailInputParamsException("Ошибка в запросе на бронирование (не указано время начала/конца бронирования)");
        }
        if (booking.getEnd().isBefore(LocalDateTime.now())) {
            throw new BookingTimeValidationException("Ошибка в запросе на бронирование (время окончания бронирования уже прошло)");
        }
        if (booking.getStart().equals(booking.getEnd())) {
            throw new BookingTimeValidationException("Ошибка в запросе на бронирование (start time = end time)");
        }
        if (booking.getStart().isBefore(LocalDateTime.now())) {
            throw new BookingTimeValidationException("Ошибка в запросе на бронирование (start time < now)");
        }
    }
    private boolean checkOwnedItemsByUser(Long userId) {
        return castToDtosAndSortByDate(bookingDao.findAllByOwner(userId)).isEmpty();
    }
    @Override
    public List<BookingDto> getAllBookingsByOwnerItems(Long id, String state) {
        if (checkOwnedItemsByUser(id)) {
            throw new UserHaveNoItemsException("Ошибка при получении items by owner (user have no items)");
        }
        if (state == null) {
            return castToDtosAndSortByDate(bookingDao.findByBooker_Id(id));
        } else {
            return switch (state) {
                case "CURRENT" -> castToDtosAndSortByDate(bookingDao.findCurrentByOwner(id, LocalDateTime.now()));
                case "PAST" -> castToDtosAndSortByDate(bookingDao.findPastByOwner(id, LocalDateTime.now()));
                case "WAITING" -> castToDtosAndSortByDate(bookingDao.findWaitingByOwner(id));
                case "REJECTED" -> castToDtosAndSortByDate(bookingDao.findRejectedByOwner(id));
                default -> castToDtosAndSortByDate(bookingDao.findAllByOwner(id));
            };
        }
    }
}
