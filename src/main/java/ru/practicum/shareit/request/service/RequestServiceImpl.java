package ru.practicum.shareit.request.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.request.dao.RequestRepository;
import ru.practicum.shareit.request.dto.RequestDto;
import ru.practicum.shareit.request.mapper.RequestMapper;
import ru.practicum.shareit.request.model.Request;
import ru.practicum.shareit.user.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final RequestRepository repository;
    private final RequestMapper mapper;
    private final UserService userService;
    //private final ItemPostgresRepository itemRepository;
    //private final ItemMapper itemMapper;
    //private final RequestWithItemsDtoMapper requestWithItemsDtoMapper;

    @Override
    public RequestDto addRequest(Long userId, RequestDto request) {
        request.setRequester(userService.read(userId));
        request.setCreated(LocalDateTime.now());
        request.setItems(null);
        return mapper.toDto(repository.save(mapper.toRequest(request)));
    }

    @Override
    public RequestDto getRequest(Long requestId) {
        return mapper.toDto(repository.getReferenceById(requestId));
    }


    @Override
    public RequestDto updateRequest(Long userId, Long requestId, RequestDto request) {
        request.setId(requestId);
        return mapper.toDto(repository.save(mapper.toRequest(request)));
    }

    @Override
    public void deleteRequest(Long userId, Long requestId) {
        repository.deleteById(requestId);
    }

    @Override
    public List<RequestDto> getAllByUser(Long userId) {
        //List<Request> requests = repository.getAllByUser(userId);
        //List<RequestWithAnswersDto> requestWithAnswers = new ArrayList<>(requests.size());
        /*return repository.getAllByUser(userId)
                .stream()
                .map(requestWithItemsDtoMapper::toRequestWithAnswersDto).collect(Collectors.toList());*/
        return requestsToDtos(repository.getAllByUser(userId));

    }

    @Override
    public List<RequestDto> getAllOthers(Long userId) {
        return requestsToDtos(repository.getAllOthers(userId));
    }

    private List<RequestDto> requestsToDtos(List<Request> requests) {
        return requests
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
