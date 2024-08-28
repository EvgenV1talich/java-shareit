package ru.practicum.shareit.request.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemRequestDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.request.service.RequestService;

@RequiredArgsConstructor
@Component
public class ItemRequestDtoMapper {

    private final RequestService requestService;
    public ItemRequestDto itemDtotoItemRequestDto(ItemDto itemDto) {
        return new ItemRequestDto(itemDto.getDescription(),
                itemDto.getOwner(),
                requestService.getRequest(itemDto.getRequestId()).getCreated());
    }
    public ItemRequestDto itemToItemRequestDto(Item item) {
        return new ItemRequestDto(item.getDescription(),
                item.getOwner(),
                requestService.getRequest(item.getRequest().getId()).getCreated());
    }

}
