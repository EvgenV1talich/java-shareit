package ru.practicum.shareit.item.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.request.mapper.RequestMapper;
import ru.practicum.shareit.request.service.RequestService;

@Component
@RequiredArgsConstructor
public class ItemMapper {

    private final RequestService requestService;
    private final RequestMapper requestMapper;
    public ItemDto toDto(Item item) {
        if (item == null) {
            throw new ResponseStatusException(HttpStatus.valueOf(500));
        }
        ItemDto dto = new ItemDto();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setDescription(item.getDescription());
        dto.setOwner(item.getOwner());
        dto.setAvailable(item.getAvailable());
        if (item.getRequest() != null) {
            dto.setRequestId(item.getRequest().getId());
        }
        return dto;
    }

    public Item toItem(ItemDto dto) {
        if (dto == null) {
            throw new ResponseStatusException(HttpStatus.valueOf(500));
        }
        Item item = new Item();
        item.setId(dto.getId());
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setAvailable(dto.getAvailable());
        item.setOwner(dto.getOwner());
        if (dto.getRequestId() != null) {
            item.setRequest(requestMapper.toRequest(requestService.getRequest(dto.getRequestId())));
        }
        return item;
    }

}
