package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.shareit.item.dao.ItemDao;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.user.dao.UserDao;

import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemDao itemDao;
    private final UserDao userDao;
    private final ItemMapper mapper;

    @PostMapping
    public void addItem(ItemDto itemDto) {
        itemDao.create(itemDto);
    }
    @PatchMapping("/{itemId}")
    public ItemDto updateItem(@PathVariable int itemId, ItemDto itemDto) {
        return null;
    }
    @GetMapping("/itemId")
    public ItemDto getItem(@PathVariable int itemId) {
        return null;
    }
    @GetMapping
    public List<ItemDto> getAllItemsByUser() {
        //TODO чёто не так с сигнатурой
        return null;
    }

}
