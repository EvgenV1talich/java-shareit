package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor;
import ru.practicum.shareit.item.dao.ItemDao;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemDao itemDao;
    @Override
    public void create(Item item) {
        itemDao.create(item);
    }

    @Override
    public Item read(Integer id) {
        return itemDao.read(id);
    }

    @Override
    public Item update(Item item) {
        return itemDao.update(item);
    }

    @Override
    public void delete(Integer id) {
        itemDao.delete(id);
    }

    @Override
    public List<Item> readAll() {
        return itemDao.readAll();
    }

    @Override
    public List<Item> readByUser(Integer userId) {
        return itemDao.readByUser(userId);
    }
}
