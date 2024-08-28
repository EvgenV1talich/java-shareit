package ru.practicum.shareit.request.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.request.dto.RequestWithAnswersDto;
import ru.practicum.shareit.request.model.Request;

@RequiredArgsConstructor
@Component
//TODO remove useless class
public class RequestWithItemsDtoMapper {
    private final ItemService itemService;
    private final ItemRequestDtoMapper itemRequestDtoMapper;

    public RequestWithAnswersDto toRequestWithAnswersDto(Request request) {
        return new RequestWithAnswersDto(request.getDescription(),
                request.getCreated(),
                request.getRequester().getId(),
                null);
    }

}
