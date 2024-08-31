package ru.practicum.shareit.item;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
@Slf4j
public class ItemController {

    private final ItemClient client;

    @PostMapping
    public ResponseEntity<Object> addItem(@RequestBody @Valid ItemDto itemDto,
                                          @RequestHeader(value = "X-Sharer-User-Id") Long userId) {
        return client.add(itemDto, userId.intValue());
    }

    @PatchMapping("/{itemId}")
    public ResponseEntity<Object> updateItem(@PathVariable Long itemId,
                                             @RequestBody ItemDto itemDto,
                                             @RequestHeader(value = "X-Sharer-User-Id") Long requestUserId) {
        return client.update(itemId.intValue(), requestUserId.intValue(), itemDto);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Object> getItem(@PathVariable Long itemId, @RequestHeader(value = "X-Sharer-User-Id") Long requestUserId) {
        return client.getById(itemId.intValue(), requestUserId.intValue());
    }

    @GetMapping
    public ResponseEntity<Object> getItemsByOwner(@RequestHeader(value = "X-Sharer-User-Id") Long userId) {
        return client.getAll(userId.intValue());
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllItems(@RequestHeader(value = "X-Sharer-User-Id") Long userId) {
        return client.getAll(userId);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchItems(@RequestParam(name = "text") String text,
                                              @RequestHeader(value = "X-Sharer-User-Id") Long userId) {
        return client.find(text, userId.intValue());
    }

    @PostMapping("/{itemId}/comment")
    public ResponseEntity<Object> addComment(@RequestHeader(value = "X-Sharer-User-Id") Long userId,
                                             @PathVariable Long itemId,
                                             @RequestBody CommentDto comment) {
        return client.addComment(itemId, userId, comment);
    }

}
