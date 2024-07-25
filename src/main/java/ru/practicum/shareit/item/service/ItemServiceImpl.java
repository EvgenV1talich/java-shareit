package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.practicum.shareit.item.ItemMapper;
import ru.practicum.shareit.item.dao.ItemDao;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.user.dao.UserDao;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemDao itemDao;
    private final UserDao userDao;

    @Override
    public ItemDto create(ItemDto item, Integer ownerId) {
        return ItemMapper.itemToDto(itemDao.create(ItemMapper.dtoToItem(item), ownerId));
    }

    @Override
    public ItemDto read(Integer id) {
        return ItemMapper.itemToDto(itemDao.read(id));
    }

    @Override
    public ItemDto update(Integer id, ItemDto item, Integer requestUserId) {
        if (!Objects.equals(id, requestUserId)) {
            throw new ResponseStatusException(HttpStatus.valueOf(403));
        }
        return ItemMapper.itemToDto(itemDao.update(id, ItemMapper.dtoToItem(item), requestUserId));
    }

    @Override
    public void delete(Integer id) {
        itemDao.delete(id);
    }

    @Override
    public List<ItemDto> readAll() {
        return itemDao.readAll().stream()
                .map(ItemMapper::itemToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemDto> readByUser(Integer userId) {
        return itemDao.readByUser(userId).stream()
                .map(ItemMapper::itemToDto)
                .collect(Collectors.toList());
    }
}
