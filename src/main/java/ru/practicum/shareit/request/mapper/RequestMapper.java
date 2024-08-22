package ru.practicum.shareit.request.mapper;

import org.mapstruct.Mapper;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.model.ItemRequest;

@Mapper
public interface RequestMapper {
    ItemRequestDto toItemDto(ItemRequest request);
    ItemRequest toItemRequest(ItemRequestDto dto);
}
