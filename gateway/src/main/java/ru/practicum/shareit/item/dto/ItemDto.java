package ru.practicum.shareit.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.booking.dto.BookingDto;

import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDto {

    private Long id;
    @NotBlank
    @NotNull
    @NotEmpty
    private String name;
    @NotBlank
    @NotNull
    @NotEmpty
    private String description;
    @NotNull
    private Boolean available;
    private Long requestId;
    private BookingDto lastBooking;
    private BookingDto nextBooking;
    private List<CommentInItem> comments;


}
