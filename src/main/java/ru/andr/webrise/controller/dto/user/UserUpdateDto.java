package ru.andr.webrise.controller.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserUpdateDto {
    private String name;

    @Min(value = 0, message = "Возраст должен быть больше или равен 0")
    private Integer age;

    @Email(message = "Email должен быть корректным")
    private String email;
}
