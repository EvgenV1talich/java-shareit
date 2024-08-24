package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.service.BookingService;
import ru.practicum.shareit.exceptions.ItemNotFoundException;
import ru.practicum.shareit.exceptions.UserNoAccessException;
import ru.practicum.shareit.exceptions.UserNotFoundException;
import ru.practicum.shareit.item.dao.CommentDao;
import ru.practicum.shareit.item.dao.ItemPSQLDao;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.mappers.CommentMapper;
import ru.practicum.shareit.item.mappers.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.dao.UserPSQLDao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Primary
public class ItemServicePSQLImpl implements ItemService {

    private final ItemPSQLDao itemDao;
    private final UserPSQLDao userDao;
    private final BookingService bookingService;
    private final CommentDao commentDao;
    private final CommentMapper commentMapper;

    @Override
    public ItemDto create(ItemDto item, Long ownerId) {
        if (!userDao.existsById(ownerId)) throw new UserNotFoundException("Такого пользователя не существует!");
        item.setOwner(userDao.getReferenceById(ownerId));
        return ItemMapper.toDto(itemDao.save(ItemMapper.toItem(item)));
    }

    @Override
    public ItemDto read(Long id) {
        if (!itemDao.existsById(id)) {
            throw new ItemNotFoundException("Ошибка при чтении item (id = " + id + ")");
        }
        return ItemMapper.toDto(itemDao.getReferenceById(id));
    }

    @Override
    public ItemDto update(Long id, ItemDto item, Long userId) {
        if (!checkOwnByUser(userId, id)) throw new UserNoAccessException("У пользователя нет доступа к item " + id);
        item.setId(id);
        boolean isNewName = item.getName() != null;
        boolean isNewDescription = item.getDescription() != null;
        boolean isNewAvailable = item.getAvailable() != null;
        boolean isNewRequest = item.getRequest() != null;
        boolean isNewOwner = item.getOwner() != null;

        Item oldItem = itemDao.getReferenceById(id);

        Item itemToUpdate = new Item();
        itemToUpdate.setId(id);
        //Name update
        if (isNewName) {
            itemToUpdate.setName(item.getName());
        } else {
            itemToUpdate.setName(oldItem.getName());
        }
        //Description update
        if (isNewDescription) {
            itemToUpdate.setDescription(item.getDescription());
        } else {
            itemToUpdate.setDescription(oldItem.getDescription());
        }
        //Available update
        if (isNewAvailable) {
            itemToUpdate.setAvailable(item.getAvailable());
        } else {
            itemToUpdate.setAvailable(oldItem.getAvailable());
        }
        //Request update
        if (isNewRequest) {
            itemToUpdate.setRequest(item.getRequest());
        } else {
            itemToUpdate.setRequest(oldItem.getRequest());
        }
        //Owner update
        if (isNewOwner) {
            itemToUpdate.setOwner(item.getOwner());
        } else {
            itemToUpdate.setOwner(oldItem.getOwner());
        }
        return ItemMapper.toDto(itemDao.save(itemToUpdate));
    }

    @Override
    public void delete(Long id) {
        itemDao.deleteById(id);
    }

    @Override
    public List<ItemDto> readAll() {
        return castItemsListToDtos(itemDao.findAll());
    }

    @Override
    public List<ItemDto> readByUser(Long userId) {
        return castItemsListToDtos(itemDao.getAllItemsByUser(userId));
    }

    @Override
    public List<ItemDto> searchItems(String request) {
        if (request.isEmpty()) return new ArrayList<>();
        String toLower = request.toLowerCase();
        return castItemsListToDtos(itemDao.searchItems(toLower));
    }

    private boolean checkOwnByUser(Long requestUserId, Long itemId) {
        return itemDao.getReferenceById(itemId)
                .getOwner()
                .getId()
                .equals(requestUserId);
    }

    public boolean exists(Long itemId) {
        return itemDao.existsById(itemId);
    }

    private List<ItemDto> castItemsListToDtos(List<Item> items) {
        return items.stream().map(ItemMapper::toDto).collect(Collectors.toList());
    }

    public CommentDto addComment(Long itemId, Long userId, CommentDto comment) {
        if (!getAvailableForCommentItems(userId).contains(itemId)) {
            throw new UserNoAccessException("Ошибка при добавлении комментария пользователем...");
        }
        return commentMapper.toDto(commentDao.save(commentMapper.toComment(comment)));
    }

    private List<Long> getAvailableForCommentItems(Long userId) {
        List<BookingDto> bookingsByUser = bookingService.getAllBookingsByUser(userId, null);
        return bookingsByUser.stream().map(BookingDto::getItemId).toList();
    }
}
