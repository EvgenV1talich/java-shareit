package ru.practicum.shareit.item.dto;

import lombok.RequiredArgsConstructor;
import ru.practicum.shareit.item.model.Item;

/**
 * TODO Sprint add-controllers.
 */
@RequiredArgsConstructor
public class ItemDto {

    private final String name;
    private final String description;
    private final String status;
    private final String request;

    public static ItemDto itemToDto(Item item) {
        return new ItemDto(item.getName(),
                item.getDescription(),
                item.getStatus(),
                item.getRequest());
    }

}
