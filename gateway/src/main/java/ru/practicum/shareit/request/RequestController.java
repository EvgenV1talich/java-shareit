package ru.practicum.shareit.request;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.shareit.request.dto.RequestDto;

/**
 * TODO Sprint add-item-requests.
 */
@RestController
@RequestMapping(path = "/requests")
@RequiredArgsConstructor
public class RequestController {

    private final RequestClient client;

    @PostMapping
    public ResponseEntity<Object> addRequest(@RequestHeader(value = "X-Sharer-User-Id") Long userId,
                                             @RequestBody RequestDto request) {
        return client.add(request, userId);
    }

    @GetMapping
    public ResponseEntity<Object> getAllRequestsByUser(@RequestHeader(value = "X-Sharer-User-Id") Long userId) {
        return client.getByUserId(userId);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAll(@RequestHeader(value = "X-Sharer-User-Id") Long userId) {
        return client.getByUserId(userId);
    }

    @GetMapping("{requestId}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "requestId") Long requestId,
                                         @RequestHeader(value = "X-Sharer-User-Id") Long userId) {
        return client.getById(userId, requestId);
    }
}
