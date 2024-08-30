package ru.practicum.shareit.item.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

@Repository
public interface ItemPostgresRepository extends JpaRepository<Item, Long> {

    @Query(value = "SELECT * FROM items WHERE owner_id = :id", nativeQuery = true)
    List<Item> getAllItemsByUser(@Param(value = "id") Long userId);

    @Query(value = "SELECT * FROM items WHERE available = true AND LOWER(item_name) LIKE CONCAT('%', :request, '%') OR LOWER(description) LIKE CONCAT('%', :request, '%')", nativeQuery = true)
    List<Item> searchItems(@Param(value = "request") String request);
    @Query(value = "SELECT * FROM items WHERE request_id = :requestId", nativeQuery = true)
    List<Item> getAllItemsByRequest(@Param(value = "requestId") Long requestId);
}
