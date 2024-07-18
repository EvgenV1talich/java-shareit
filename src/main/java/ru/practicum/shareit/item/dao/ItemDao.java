package ru.practicum.shareit.item.dao;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

@Repository
public interface ItemDao {

    void create(Item item);

    Item read(Integer id);

    Item update(Item item);

    void delete(Integer id);

    List<Item> readAll();

    List<Item> readByUser(Integer userId);

}
