package ru.practicum.shareit.item.dao;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

@Repository
public interface ItemDao {

    Item create(Item item, Integer ownerId);

    Item read(Integer id);

    Item update(Integer id, Item item, Integer requestUserId);

    void delete(Integer id);

    List<Item> readAll();

    List<Item> readByUser(Integer userId);

}
