package ru.practicum.shareit.booking.service;

import lombok.RequiredArgsConstructor;
import ru.practicum.shareit.booking.dao.BookingDao;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.mapper.BookingMapper;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.BookingStatus;
import ru.practicum.shareit.exceptions.UserNoAccessException;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.user.service.UserService;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class BookingServicePSQLImpl implements BookingService {
    private final BookingDao bookingDao;
    private final UserService userService;
    private final ItemService itemService;
    private final BookingMapper bookingMapper;

    @Override
    public BookingDto addRequest(Booking request) {
        request.setStatus(BookingStatus.WAITING);
        return bookingMapper.toDto(bookingDao.save(request));
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
        if (isApproved) {
            Booking updatedBooking = bookingDao.getReferenceById(bookingId);
            updatedBooking.setStatus(BookingStatus.APPOROVED);
            return bookingMapper.toDto(bookingDao.save(updatedBooking));
        } else {
            Booking updatedBooking = bookingDao.getReferenceById(bookingId);
            updatedBooking.setStatus(BookingStatus.REJECTED);
            return bookingMapper.toDto(bookingDao.save(updatedBooking));
        }
    }

    @Override
    public BookingDto getInfoForRequest(Long id, Long requesterId) {
        if (!requesterId.equals(bookingDao.getReferenceById(id).getItem().getOwner().getId()) ||
                !requesterId.equals(bookingDao.getReferenceById(id).getBooker().getId())) {
            throw new UserNoAccessException("Ошибка при получении инфо о бронировании (нет доступа)...");
        }
        return bookingMapper.toDto(bookingDao.getReferenceById(id));
    }

    @Override
    public List<BookingDto> getAllBookingsByUser(Long id, String state) {
        return switch (state) {
            case "CURRENT" -> castToDtosAndSortByDate(bookingDao.findCurrentByUser(id, LocalDateTime.now()));
            case "PAST" -> castToDtosAndSortByDate(bookingDao.findPastByUser(id, LocalDateTime.now()));
            case "WAITING" -> castToDtosAndSortByDate(bookingDao.findWaitingByUser(id));
            case "REJECTED" -> castToDtosAndSortByDate(bookingDao.findRejectedByUser(id));
            default -> castToDtosAndSortByDate(bookingDao.findByBooker_Id(id));
        };
    }

    private List<BookingDto> castToDtosAndSortByDate(List<Booking> bookings) {
        return bookings.stream()
                .sorted(Comparator.comparing(Booking::getEnd))
                .map(bookingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingDto> getAllBookingsByOwnerItems(Long id) {
        //TODO Получение списка бронирований для всех вещей текущего пользователя.
        // Эндпоинт — GET /bookings/owner?state={state}.
        // Этот запрос имеет смысл для владельца хотя бы одной вещи.
        // Работа параметра state аналогична его работе в getAllBookingsByUser
        return null;
    }
}
