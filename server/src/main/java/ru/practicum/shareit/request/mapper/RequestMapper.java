package ru.practicum.shareit.request.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.request.dto.RequestDto;
import ru.practicum.shareit.request.model.Request;
import ru.practicum.shareit.user.service.UserService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RequestMapper {

    private final UserService userService;


    public Request toRequest(RequestDto dto) {
        Request request = new Request();
        if (dto.getId() != null) request.setId(dto.getId());
        if (dto.getCreated() != null) request.setCreated(dto.getCreated());
        if (dto.getDescription() != null) request.setDescription(dto.getDescription());
        if (dto.getRequester() != null) request.setRequester(userService.read(dto.getRequester()));
        return request;
    }

    public RequestDto toDto(Request request) {
        return new RequestDto(request.getId(),
                request.getCreated(),
                request.getDescription(),
                request.getRequester().getId(), null, null);
    }

    public RequestDto toDtoWithItems(Request request, List<ItemDto> items, Long ownerId) {
        return new RequestDto(request.getId(),
                request.getCreated(),
                request.getDescription(),
                request.getRequester().getId(),
                items,
                ownerId);
    }

}
