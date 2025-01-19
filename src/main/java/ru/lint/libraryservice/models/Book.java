package ru.lint.libraryservice.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    private Integer bookId;
    private Integer userId;

    @NotEmpty
    @Size(min = 1, max = 255, message = "The book name must not be empty and must not exceed 255 characters")
    private String name;

    @NotEmpty
    @Size(min = 1, max = 255, message = "The author name must not be empty and must not exceed 255 characters")
    private String authorName;

    @Min(value = 1901, message = "The minimum value for the year of creating is 1901")
    @Max(value = 2025, message = "The maximum value for the year of creating is 2025")
    private Integer creatingYear;
}
