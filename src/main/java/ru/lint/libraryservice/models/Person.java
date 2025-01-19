package ru.lint.libraryservice.models;


import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Person {
    private Integer id;

    @NotEmpty
    @Size(min = 1, max = 255, message = "The name must not be empty and must not exceed 255 characters")
    private String name;

    @NotEmpty
    @Size(min = 1, max = 255, message = "The surname must not be empty and must not exceed 255 characters")
    private String surname;

    @NotEmpty
    @Size(min = 1, max = 255, message = "The patronymic must not be empty and must not exceed 255 characters")
    private String patronymic;

    @Min(value = 1901, message = "The minimum value for the year of birth is 1901")
    @Max(value = 2025, message = "The maximum value for the year of birth is 2025")
    private Integer birthYear;
}