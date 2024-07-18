package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor;
import ru.practicum.shareit.item.dao.ItemDao;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemDao itemDao;
    @Override
    public void create(ItemDto item) {
        itemDao.create(item);
    }

    @Override
    public Item read(Integer id) {
        return itemDao.read(id);
    }

    @Override
    public Item update(ItemDto item) {
        return itemDao.update(item);
    }

    @Override
    public void delete(Integer id) {
        itemDao.delete(id);
    }

    @Override
    public List<ItemDto> readAll() {
        return itemDao.readAll();
    }

    @Override
    public List<ItemDto> readByUser(Integer userId) {
        return itemDao.readByUser(userId);
    }
}
