package ru.practicum.shareit.user;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping(path = "/users")
public class UserController {

    @PostMapping
    public void createUser(User user) {

    }
    @GetMapping
    public User readUser(Integer id) {
        return null;
    }
    @PatchMapping
    public User updateUser(User user) {
        return null;
    }
    @DeleteMapping
    public void deleteUser(Integer id) {

    }


}
