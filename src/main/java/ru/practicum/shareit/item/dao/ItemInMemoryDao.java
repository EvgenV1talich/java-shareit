package ru.practicum.shareit.item.dao;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;
import ru.practicum.shareit.item.model.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ItemInMemoryDao implements ItemDao {

    private final Set<Integer> indexes = new TreeSet<>();
    private Integer lastAddedIndex;

    private final HashMap<Integer, Item> items = new HashMap<>();

    public ItemInMemoryDao() {
        lastAddedIndex = 0;
        indexes.add(lastAddedIndex);
    }

    @Override
    public void create(Item item) {
        items.put(item.getId(), item);
    }

    @Override
    public Item read(Integer id) {
        checkIndex(id);
        return items.get(id);
    }

    @Override
    public Item update(Item item) {
        checkIndex(item.getId());
        items.replace(item.getId(), item);
        return items.get(item.getId());
    }

    @Override
    public void delete(Integer id) {
        checkIndex(id);
        items.remove(id);
    }

    @Override
    public List<Item> readAll() {
        return new ArrayList<>(items.values());
    }

    @Override
    public List<Item> readByUser(Integer userId) {
        List<Item> userItems = new ArrayList<>();
        return items.values().stream()
                .filter(item -> item.getOwner().getId() == userId)
                .collect(Collectors.toList());
    }

    private void checkIndex(int newIndex) {
        if (indexes.contains(newIndex)) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Item not found!");
        }
    }

    private Integer getNewIndex() {
        Integer newIndex = ++lastAddedIndex;
        indexes.add(newIndex);
        return lastAddedIndex;
    }
}
