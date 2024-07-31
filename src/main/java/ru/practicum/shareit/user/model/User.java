package ru.practicum.shareit.user.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * TODO Sprint add-controllers.
 */
@Data
public class User {

    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User() {
    }

    private Long id;
    @NotEmpty
    @NotNull
    @NotBlank
    private String name;
    @Email
    @NotEmpty
    @NotNull
    @NotBlank
    private String email;

}
