package ru.practicum.shareit.user.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.dto.UserDto;

@Component
public class UserMapper {
    public static UserDto userToDto(User user) {
        return new UserDto(user.getName(),
                user.getEmail());
    }

    public static User dtotoUser(UserDto dto) {
        return new User(null, dto.getName(), dto.getEmail());
    }
}
