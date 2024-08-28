package ru.practicum.shareit.request.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.request.model.Request;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    @Query(value = "SELECT * FROM requests WHERE requester_id = :userId", nativeQuery = true)
    List<Request> getAllByUser(@Param(value = "userId") Long userId);

    @Query(value = "SELECT * FROM requests WHERE requester_id != :userId")
    List<Request> getAllOthers(@Param(value = "userId") Long userId);

}
