package ru.practicum.shareit.booking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.booking.dao.BookingRepository;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.mapper.BookingMapper;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.BookingStatus;
import ru.practicum.shareit.exceptions.item.ItemNotAvailableException;
import ru.practicum.shareit.exceptions.user.UserHaveNoItemsException;
import ru.practicum.shareit.exceptions.user.UserNoAccessException;
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
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final UserService userService;
    private final ItemService itemService;
    private final BookingMapper bookingMapper;
    private final ItemMapper itemMapper;

    @Override
    public BookingDto addRequest(BookingDto request, Long bookerId) {
        Item item = itemMapper.toItem(itemService.read(request.getItemId()));
        User booker = userService.read(bookerId);
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
        return bookingMapper.toDto(bookingRepository.save(newBooking));
    }

    @Override
    public BookingDto approveRequest(Long requestOwnerId, Long bookingId, Boolean isApproved) {
        if (!requestOwnerId.equals(bookingRepository
                .getReferenceById(bookingId)
                .getItem()
                .getOwner()
                .getId())) {
            throw new UserNoAccessException("Ошибка при подтверждении запроса бронирования (нет доступа)...");
        }
        Booking updatedBooking = bookingRepository.getReferenceById(bookingId);
        if (isApproved) {
            updatedBooking.setStatus(BookingStatus.APPROVED);
        } else {
            updatedBooking.setStatus(BookingStatus.REJECTED);
        }
        BookingDto updatedBookingDto = bookingMapper.toDto(updatedBooking);
        updatedBookingDto.setItemId(updatedBooking.getItem().getId());
        bookingRepository.save(updatedBooking);
        return updatedBookingDto;
    }

    @Override
    public BookingDto getInfoForRequest(Long id, Long requesterId) {
        if (requesterId.equals(bookingRepository.getReferenceById(id).getItem().getOwner().getId()) ||
                requesterId.equals(bookingRepository.getReferenceById(id).getBooker().getId())) {
            return bookingMapper.toDto(bookingRepository.getReferenceById(id));
        } else {
            throw new UserNoAccessException("Ошибка при получении инфо о бронировании (нет доступа)...");
        }
    }

    @Override
    public List<BookingDto> getAllBookingsByUser(Long id, String state) {
        if (state == null) {
            return castToDtosAndSortByDate(bookingRepository.findByBooker_Id(id));
        } else {
            return switch (state) {
                case "CURRENT" -> castToDtosAndSortByDate(bookingRepository.findCurrentByUser(id, LocalDateTime.now()));
                case "PAST" -> castToDtosAndSortByDate(bookingRepository.findPastByUser(id, LocalDateTime.now()));
                case "WAITING" -> castToDtosAndSortByDate(bookingRepository.findWaitingByUser(id));
                case "REJECTED" -> castToDtosAndSortByDate(bookingRepository.findRejectedByUser(id));
                default -> castToDtosAndSortByDate(bookingRepository.findByBooker_Id(id));
            };
        }
    }

    private List<BookingDto> castToDtosAndSortByDate(List<Booking> bookings) {
        return bookings.stream()
                .sorted(Comparator.comparing(Booking::getEnd))
                .map(bookingMapper::toDto)
                .collect(Collectors.toList());
    }

    private boolean checkOwnedItemsByUser(Long userId) {
        return castToDtosAndSortByDate(bookingRepository.findAllByOwner(userId)).isEmpty();
    }

    @Override
    public List<BookingDto> getAllBookingsByOwnerItems(Long id, String state) {
        if (checkOwnedItemsByUser(id)) {
            throw new UserHaveNoItemsException("Ошибка при получении items by owner (user have no items)");
        }
        if (state == null) {
            return castToDtosAndSortByDate(bookingRepository.findByBooker_Id(id));
        } else {
            return switch (state) {
                case "CURRENT" ->
                        castToDtosAndSortByDate(bookingRepository.findCurrentByOwner(id, LocalDateTime.now()));
                case "PAST" -> castToDtosAndSortByDate(bookingRepository.findPastByOwner(id, LocalDateTime.now()));
                case "WAITING" -> castToDtosAndSortByDate(bookingRepository.findWaitingByOwner(id));
                case "REJECTED" -> castToDtosAndSortByDate(bookingRepository.findRejectedByOwner(id));
                default -> castToDtosAndSortByDate(bookingRepository.findAllByOwner(id));
            };
        }
    }
}
