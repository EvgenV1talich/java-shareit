package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.shareit.user.service.UserService;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserController {

    private final UserService service;

    @PostMapping
    public void createUser(User user) {
        service.create(user);
    }

    @GetMapping
    public User readUser(Integer id) {
        return service.read(id);
    }

    @PatchMapping
    public void updateUser(User user) {
        service.update(user);
    }

    @DeleteMapping
    public void deleteUser(Integer id) {
        service.delete(id);
    }

}
