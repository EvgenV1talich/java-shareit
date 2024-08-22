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

    @Query("SELECT * FROM bookings WHERE booker_id == :requesterId AND end_time <= :endTime")
    List<Booking> findCurrentByUser(@Param("requesterId") Long requesterId, @Param("endTime") LocalDateTime endTime);

    @Query("SELECT * FROM bookings WHERE booker_id == :requesterId AND end_time > :endTime")
    List<Booking> findPastByUser(@Param("requesterId") Long requesterId, @Param("endTime") LocalDateTime endTime);

    @Query("SELECT * FROM bookings WHERE booker_id == :requesterId AND status == 'WAITING'")
    List<Booking> findWaitingByUser(Long requesterId);

    @Query("SELECT * FROM bookings WHERE booker_id == :requesterId AND status == 'REJECTED'")
    List<Booking> findRejectedByUser(Long requesterId);
}
