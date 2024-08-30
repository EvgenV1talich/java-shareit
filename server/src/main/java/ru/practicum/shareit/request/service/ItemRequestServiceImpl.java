package ru.practicum.shareit.request.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.request.dao.RequestRepository;
import ru.practicum.shareit.request.dto.RequestDto;
import ru.practicum.shareit.request.mapper.RequestMapper;
import ru.practicum.shareit.user.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemRequestServiceImpl implements ItemRequestService {


    private final ItemService itemService;
    private final RequestService requestService;
    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;
    private final UserService userService;

    @Override
    public RequestDto addRequest(Long userId, RequestDto request) {
        request.setRequester(userService.read(userId));
        request.setCreated(LocalDateTime.now());
        request.setItems(itemService.getItemsByRequest(request.getId()));
        return requestMapper.toDto(requestRepository.save(requestMapper.toRequest(request)));
    }

    @Override
    public List<RequestDto> getMyRequestWithItems(Long userId) {
        List<RequestDto> requests = requestRepository.getAllByUser(userId).stream().map(requestMapper::toDto).toList();
        return requests.stream().peek(requestDto -> {
            List<ItemDto> items = itemService.getItemsByRequest(requestDto.getId());
            requestDto.setItems(items);
        }).collect(Collectors.toList());
    }

    @Override
    public List<RequestDto> getOtherRequestsWithItems(Long userId) {
        List<RequestDto> requests = requestRepository.getAllOthers(userId).stream().map(requestMapper::toDto).toList();
        return requests.stream().peek(requestDto -> {
            List<ItemDto> items = itemService.getItemsByRequest(requestDto.getId());
            requestDto.setItems(items);
        }).collect(Collectors.toList());
    }

    @Override
    public RequestDto getOneRequest(Long requestId) {
        RequestDto dto = requestService.getRequest(requestId);
        dto.setItems(itemService.getItemsByRequest(dto.getId()));
        return dto;
    }
}
