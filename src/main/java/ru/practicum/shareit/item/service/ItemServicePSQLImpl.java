package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exceptions.UserNoAccessException;
import ru.practicum.shareit.exceptions.UserNotFoundException;
import ru.practicum.shareit.item.ItemMapper;
import ru.practicum.shareit.item.dao.ItemPSQLDao;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.user.dao.UserPSQLDao;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Primary
public class ItemServicePSQLImpl implements ItemService {

    private final ItemPSQLDao itemDao;
    private final UserPSQLDao userDao;
    @Override
    public ItemDto create(ItemDto item, Long ownerId) {
        if (!userDao.existsById(ownerId)) throw new UserNotFoundException("Такого пользователя не существует!");
        return ItemMapper.toDto(itemDao.save(ItemMapper.toItem(item)));
    }

    @Override
    public ItemDto read(Long id) {
        return ItemMapper.toDto(itemDao.getReferenceById(id));
    }

    @Override
    public ItemDto update(Long id, ItemDto item, Long userId) {
        if (!checkOwnByUser(id, userId)) throw new UserNoAccessException("У пользователя нет доступа к item " + id);
        return ItemMapper.toDto(itemDao.save(ItemMapper.toItem(item)));
    }

    @Override
    public void delete(Long id) {
        itemDao.deleteById(id);
    }

    @Override
    public List<ItemDto> readAll() {
        return itemDao.findAll().stream()
                .map(ItemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemDto> readByUser(Long userId) {
        return null;
    }

    @Override
    public List<ItemDto> searchItems(String request) {
        return null;
    }

    private boolean checkOwnByUser(Long requestUserId, Long itemId) {
        return itemDao.getReferenceById(itemId)
                .getOwner()
                .getId()
                .equals(requestUserId);
    }
}
