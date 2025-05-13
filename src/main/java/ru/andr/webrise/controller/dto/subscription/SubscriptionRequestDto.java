package ru.andr.webrise.controller.dto.subscription;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionRequestDto {
    @NotEmpty(message = "Имя подписки не должно быть пустым")
    private String subscriptionName;
}
