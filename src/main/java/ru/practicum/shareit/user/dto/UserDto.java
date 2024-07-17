package ru.practicum.shareit.user.dto;

import lombok.RequiredArgsConstructor;
import ru.practicum.shareit.user.User;

@RequiredArgsConstructor
public class UserDto {

    private final String name;
    private final String email;

    public static UserDto userToDto(User user) {
        return new UserDto(user.getName(),
                user.getEmail());
    }
}
