package ru.andr.webrise.controller.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    @NotEmpty(message = "Имя не должно быть пустым")
    private String name;

    @NotNull(message = "Возраст не должен быть пустым")
    @Min(value = 0, message = "Возраст должен быть больше или равен 0")
    private Integer age;

    @NotEmpty(message = "Email не должен быть пустым")
    @Email(message = "Email должен быть корректным")
    private String email;
}
