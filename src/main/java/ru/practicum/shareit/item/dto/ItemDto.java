package ru.practicum.shareit.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.practicum.shareit.user.User;

/**
 * TODO Sprint add-controllers.
 */
@RequiredArgsConstructor
@Data
public class ItemDto {

    private final Integer id;
    @NotBlank
    @NotNull
    @NotEmpty
    private final String name;
    @NotBlank
    @NotNull
    @NotEmpty
    private final String description;
    @NotNull
    private final Boolean available;
    private final User owner;
    private final String request;



}
