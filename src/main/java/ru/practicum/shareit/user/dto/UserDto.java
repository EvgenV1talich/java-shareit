package ru.practicum.shareit.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserDto {

    @NotEmpty
    @NotNull
    @NotBlank
    private final String name;
    @Email
    @NotEmpty
    @NotNull
    @NotBlank
    private final String email;

}
