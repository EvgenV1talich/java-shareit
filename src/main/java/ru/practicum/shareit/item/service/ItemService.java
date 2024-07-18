package ru.practicum.shareit.item.service;

import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.List;
@Service
public interface ItemService {

    void create(ItemDto item);

    Item read(Integer id);

    Item update(ItemDto item);

    void delete(Integer id);

    List<ItemDto> readAll();

    List<ItemDto> readByUser(Integer userId);

}
