package ru.practicum.shareit.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.shareit.client.BaseClient;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;

import java.util.Map;

@Service
public class ItemClient extends BaseClient {
    private static final String API_PREFIX = "/items";

    @Autowired
    public ItemClient(@Value("${shareit-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(() -> new HttpComponentsClientHttpRequestFactory())
                        .build()
        );
    }

    public ResponseEntity<Object> add(ItemDto itemDto, int ownerId) {
        return post("", ownerId, itemDto);
    }

    public ResponseEntity<Object> update(int itemId, int ownerId, ItemDto itemDto) {
        return patch("/" + itemId, ownerId, itemDto);
    }

    public ResponseEntity<Object> getById(int itemId, int userId) {
        return get("/" + itemId, userId);
    }

    public ResponseEntity<Object> getAll(long ownerId) {
        return get("", ownerId);
    }

    public ResponseEntity<Object> find(String text, long userId) {
        Map<String, Object> parameters = Map.of("text", text);

        return get("/search?text=" + text, userId, parameters);
    }

    public ResponseEntity<Object> addComment(long itemId, long userId, CommentDto comment) {
        return post("/" + itemId + "/comment", userId, comment);
    }

}