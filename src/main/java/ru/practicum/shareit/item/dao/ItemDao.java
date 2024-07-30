package ru.practicum.shareit.item.dao;

import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import java.util.List;


public interface ItemDao {

    Item create(Item item, User owner);

    Item read(Long id);

    Item update(Long id, Item item, Long requestUserId, User user);

    void delete(Long id);

    List<Item> readAll();

    List<Item> readByUser(Long userId);

    List<Item> searchItems(String request);

}
