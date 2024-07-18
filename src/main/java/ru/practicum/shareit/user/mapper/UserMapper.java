package ru.practicum.shareit.user.mapper;

import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.dto.UserDto;

public class UserMapper {
    public static UserDto userToDto(User user) {
        return new UserDto(user.getName(),
                user.getEmail());
    }
    public static User dtotoUser(UserDto dto) {
        return null;
    }
}
