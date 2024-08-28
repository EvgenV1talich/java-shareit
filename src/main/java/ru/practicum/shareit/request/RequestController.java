package ru.practicum.shareit.request;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.shareit.request.dto.RequestDto;
import ru.practicum.shareit.request.service.RequestService;

import java.util.List;

/**
 * TODO Sprint add-item-requests.
 */
@RestController
@RequestMapping(path = "/requests")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService service;

    @PostMapping
    public RequestDto addRequest(@RequestHeader(value = "X-Sharer-User-Id") Long userId,
                                 @RequestBody RequestDto request) {
        return service.addRequest(userId, request);
    }

    @GetMapping
    public List<RequestDto> getAllRequestsByUser(@RequestHeader(value = "X-Sharer-User-Id") Long userId) {
        return service.getAllByUser(userId);
    }

    @GetMapping
    public List<RequestDto> getAll(@RequestHeader(value = "X-Sharer-User-Id") Long userId) {
        return service.getAllOthers(userId);
    }

    @GetMapping("{requestId}")
    public RequestDto getOne(@PathVariable(value = "requestId") Long requestId) {
        return service.getRequest(requestId);
    }
}
