package ru.practicum.shareit.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.service.UserService;

import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserController {

    private final UserService service;

    @PostMapping
    public User createUser(@RequestBody @Valid UserDto user) {
        return service.create(user);
    }

    @GetMapping("/{id}")
    public UserDto readUser(@PathVariable Long id) {
        return UserMapper.toDto(service.read(id));
    }

    @PatchMapping("/{id}")
    public UserDto updateUser(@RequestBody @Valid UserDto user, @PathVariable Long id) {
        return UserMapper.toDto(service.update(user, id));
    }

    @GetMapping
    public List<User> getAllUsers() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        service.delete(id);
    }

}
