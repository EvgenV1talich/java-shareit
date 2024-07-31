package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;

public interface ItemService {

    ItemDto create(ItemDto item, Long ownerId);

    ItemDto read(Long id);

    ItemDto update(Long id, ItemDto item, Long userId);

    void delete(Long id);

    List<ItemDto> readAll();

    List<ItemDto> readByUser(Long userId);

    List<ItemDto> searchItems(String request);

}
