package ru.practicum.shareit.item.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.practicum.shareit.user.User;

/**
 * TODO Sprint add-controllers.
 */
@RequiredArgsConstructor
@Data
public class ItemDto {

    private final int id;
    private final String name;
    private final String description;
    private final String status;
    private final User owner;
    private final String request;

}
