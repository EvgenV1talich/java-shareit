package ru.practicum.shareit.request.dto;

import lombok.RequiredArgsConstructor;
import ru.practicum.shareit.request.model.ItemRequest;
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

    public static ItemRequestDto itemRequestToDto(ItemRequest itemRequest) {
        return new ItemRequestDto(itemRequest.getDescription(),
                itemRequest.getRequester(),
                itemRequest.getCreated());
    }

}
