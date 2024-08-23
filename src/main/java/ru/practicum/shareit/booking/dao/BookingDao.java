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
}
