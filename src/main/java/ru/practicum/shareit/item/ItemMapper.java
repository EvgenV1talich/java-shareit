package ru.practicum.shareit.item;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

@Component
public class ItemMapper {

    public static ItemDto toDto(Item item) {
        if (item == null) {
            throw new ResponseStatusException(HttpStatus.valueOf(500));
        }
        ItemDto dto = new ItemDto();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setDescription(item.getDescription());
        dto.setOwner(item.getOwner());
        dto.setAvailable(item.getAvailable());
        dto.setRequest(item.getRequest());
        return dto;
    }

    public static Item toItem(ItemDto dto) {
        if (dto == null) {
            throw new ResponseStatusException(HttpStatus.valueOf(500));
        }
        Item item = new Item();
        item.setId(dto.getId());
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setAvailable(dto.getAvailable());
        item.setOwner(dto.getOwner());
        item.setRequest(dto.getRequest());
        return item;
    }

}
