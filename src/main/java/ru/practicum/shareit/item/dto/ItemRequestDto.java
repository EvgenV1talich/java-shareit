package ru.practicum.shareit.item.dto;

import lombok.RequiredArgsConstructor;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;

/**
 * TODO Sprint add-item-requests.
 */
@RequiredArgsConstructor
public class ItemRequestDto {

    private final String description;
    private final User requester;
    private final LocalDateTime created;

}
