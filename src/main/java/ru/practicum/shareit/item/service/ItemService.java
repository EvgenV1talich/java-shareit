package ru.practicum.shareit.item.service;

import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.model.Item;

import java.util.List;
@Service
public interface ItemService {

    void create(Item item);

    Item read(Integer id);

    Item update(Item item);

    void delete(Integer id);

    List<Item> readAll();

    List<Item> readByUser(Integer userId);

}
