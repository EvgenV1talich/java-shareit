package ru.practicum.shareit.user;

import lombok.Data;

/**
 * TODO Sprint add-controllers.
 */
@Data
public class User {

    public User(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    private Integer id;
    private String name;
    private String email;

}
