package ru.practicum.shareit.user.mapper;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.dto.UserDto;

@Component
public class UserMapper {
    public static UserDto toDto(User user) {
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.valueOf(500));
        }
        UserDto dto = new UserDto();
        dto.setId(String.valueOf(user.getId()));
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public static User toUser(UserDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return user;
    }
}
