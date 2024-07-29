package ru.practicum.shareit.item.dao;

import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;

import java.util.List;


public interface ItemDao {

    Item create(Item item, User owner);

    Item read(Integer id);

    Item update(Integer id, Item item, Integer requestUserId, User user);

    void delete(Integer id);

    List<Item> readAll();

    List<Item> readByUser(Integer userId);

    List<Item> searchItems(String request);

}
