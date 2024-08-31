package ru.practicum.shareit.item.mappers;

import org.mapstruct.Mapper;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.model.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment toComment(CommentDto dto);

    CommentDto toDto(Comment comment);
}
