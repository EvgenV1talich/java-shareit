package ru.practicum.shareit.item.mappers;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.exceptions.item.ItemCommentNotAvailableException;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.CommentInItem;

@Component
public class CommentInItemMapper {

    public CommentInItem toCommentItem(CommentDto dto) {
        if (dto == null) {
            throw new ItemCommentNotAvailableException("Ошибка при маппинге из DTO в CommentItem");
        }
        return new CommentInItem(dto.getId(), dto.getText(), dto.getAuthor().getName(), dto.getCreated());
    }

}
