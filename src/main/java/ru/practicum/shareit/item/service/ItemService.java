package ru.practicum.shareit.item.service;

import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;

@Service
public interface ItemService {

    ItemDto create(ItemDto item, Integer ownerId);

    ItemDto read(Integer id);

    ItemDto update(Integer id, ItemDto item, Integer userId);

    void delete(Integer id);

    List<ItemDto> readAll();

    List<ItemDto> readByUser(Integer userId);

    List<ItemDto> searchItems(String request);

}
