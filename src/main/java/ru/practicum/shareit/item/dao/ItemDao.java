package ru.practicum.shareit.item.dao;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

@Repository
public interface ItemDao {

    void create(ItemDto item);

    Item read(Integer id);

    Item update(ItemDto item);

    void delete(Integer id);

    List<ItemDto> readAll();

    List<ItemDto> readByUser(Integer userId);

}
