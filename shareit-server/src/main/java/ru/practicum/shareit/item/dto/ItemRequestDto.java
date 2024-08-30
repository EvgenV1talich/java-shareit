package ru.practicum.shareit.item.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;
import java.util.List;

/**
 * TODO Sprint add-item-requests.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequestDto {

    private String description;
    private User owner;
    private LocalDateTime created;
    List<ItemDto> items;

}
