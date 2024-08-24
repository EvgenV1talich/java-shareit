package ru.practicum.shareit.booking.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.booking.model.Booking;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingDao extends JpaRepository<Booking, Long> {
    List<Booking> findByBooker_Id(Long bookerId);

    @Query(value = "SELECT * FROM bookings WHERE booker_id == :requesterId AND end_time <= :endTime", nativeQuery = true)
    List<Booking> findCurrentByUser(@Param("requesterId") Long requesterId, @Param("endTime") LocalDateTime endTime);

    @Query(value = "SELECT * FROM bookings WHERE booker_id == :requesterId AND end_time > :endTime", nativeQuery = true)
    List<Booking> findPastByUser(@Param("requesterId") Long requesterId, @Param("endTime") LocalDateTime endTime);

    @Query(value = "SELECT * FROM bookings WHERE booker_id == :requesterId AND status == 'WAITING'", nativeQuery = true)
    List<Booking> findWaitingByUser(Long requesterId);

    @Query(value = "SELECT * FROM bookings WHERE booker_id == :requesterId AND status == 'REJECTED'", nativeQuery = true)
    List<Booking> findRejectedByUser(Long requesterId);

    @Query(value = "SELECT b.* " +
            "FROM bookings b JOIN items i ON b.item_id = i.id " +
            "WHERE i.owner_id = :userId", nativeQuery = true)
    List<Booking> findAllByOwner(@Param("userId") Long userId);

    @Query(value = "SELECT b.* " +
            "FROM bookings b JOIN items i ON b.item_id = i.id " +
            "WHERE i.owner_id = :userId AND end_time <= :endTime", nativeQuery = true)
    List<Booking> findCurrentByOwner(@Param("userId") Long userId, @Param("endTime") LocalDateTime endTime);

    @Query(value = "SELECT b.* " +
            "FROM bookings b JOIN items i ON b.item_id = i.id " +
            "WHERE i.owner_id = :userId AND status == 'WAITING'", nativeQuery = true)
    List<Booking> findWaitingByOwner(@Param("userId") Long userId);

    @Query(value = "SELECT b.* " +
            "FROM bookings b JOIN items i ON b.item_id = i.id " +
            "WHERE i.owner_id = :userId AND status == 'REJECTED'", nativeQuery = true)
    List<Booking> findRejectedByOwner(@Param("userId") Long userId);

    @Query(value = "SELECT b.* " +
            "FROM bookings b JOIN items i ON b.item_id = i.id " +
            "WHERE i.owner_id = :userId AND end_time < :endTime", nativeQuery = true)
    List<Booking> findPastByOwner(@Param("userId") Long userId, @Param("endTime") LocalDateTime endTime);

    @Query(value = "SELECT b.* " +
            "FROM bookings b JOIN items i ON b.item_id = i.id " +
            "WHERE i.owner_id = :userId AND start_time > :startTime", nativeQuery = true)
    List<Booking> findFutureByOwner(@Param("userId") Long userId, @Param("startTime") LocalDateTime startTime);


}
