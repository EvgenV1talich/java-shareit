package ru.practicum.shareit.request.service;

import ru.practicum.shareit.request.dto.RequestDto;

import java.util.List;

public interface RequestService {

    RequestDto addRequest(Long userId, RequestDto request);

    RequestDto getRequest(Long requestId);

    RequestDto updateRequest(Long userId, Long requestId, RequestDto request);

    void deleteRequest(Long userId, Long requestId);

    List<RequestDto> getAllByUser(Long userId);

    List<RequestDto> getAllOthers(Long userId);

}
