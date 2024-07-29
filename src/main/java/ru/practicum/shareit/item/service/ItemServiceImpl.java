package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exceptions.UserNoAccessException;
import ru.practicum.shareit.item.ItemMapper;
import ru.practicum.shareit.item.dao.ItemDao;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.user.dao.UserDao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemDao itemDao;
    private final UserDao userDao;

    @Override
    public ItemDto create(ItemDto item, Long ownerId) {
        return ItemMapper.itemToDto(itemDao.create(ItemMapper.dtoToItem(item), userDao.read(ownerId)));
    }

    @Override
    public ItemDto read(Long id) {
        return ItemMapper.itemToDto(itemDao.read(id));
    }

    @Override
    public ItemDto update(Long itemId, ItemDto item, Long requestUserId) {
        if (!checkOwnByUser(requestUserId, itemId)) {
            throw new UserNoAccessException("У пользователя нет доступа к item " + itemId);
        }
        return ItemMapper.itemToDto(itemDao.update(itemId, ItemMapper.dtoToItem(item), requestUserId, userDao.read(requestUserId)));
    }

    @Override
    public void delete(Long id) {
        itemDao.delete(id);
    }

    @Override
    public List<ItemDto> readAll() {
        return itemDao.readAll().stream()
                .map(ItemMapper::itemToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemDto> readByUser(Long userId) {
        return itemDao.readByUser(userId).stream()
                .map(ItemMapper::itemToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemDto> searchItems(String request) {
        String formatRequest = request.toLowerCase();
        if (formatRequest.isBlank() || formatRequest.isEmpty()) {
            return new ArrayList<>();
        }
        return itemDao.searchItems(request).stream()
                .map(ItemMapper::itemToDto)
                .collect(Collectors.toList());
    }

    private boolean checkOwnByUser(Long requestUserId, Long itemId) {
        return read(itemId).getOwner().getId().equals(requestUserId);
    }
}
