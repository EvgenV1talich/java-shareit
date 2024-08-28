package ru.practicum.shareit.request.service;

import ru.practicum.shareit.request.dto.RequestDto;

import java.util.List;

public interface ItemRequestService {

    RequestDto addRequest(Long userId, RequestDto request);
    List<RequestDto> getMyRequestWithItems(Long userId);

    List<RequestDto> getOtherRequestsWithItems(Long userId);

    RequestDto getOneRequest(Long requestId);

}
