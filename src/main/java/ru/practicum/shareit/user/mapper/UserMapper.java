package ru.practicum.shareit.user.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.exceptions.UserCreateException;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.dto.UserDto;

@Component
public class UserMapper {
    public static UserDto toDto(User user) {
        if (user == null) {
            throw new UserCreateException("Ошибка маппинга пользователя");
        }
        UserDto dto = new UserDto();
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public static User toUser(UserDto dto) {
        return new User(null, dto.getName(), dto.getEmail());
    }
}
