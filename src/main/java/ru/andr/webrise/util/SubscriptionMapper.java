package ru.andr.webrise.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.andr.webrise.controller.dto.subscription.SubscriptionRequestDto;
import ru.andr.webrise.controller.dto.subscription.SubscriptionResponseDto;
import ru.andr.webrise.controller.dto.user.UserResponseDto;
import ru.andr.webrise.model.Subscription;
import ru.andr.webrise.model.User;

@Component
@RequiredArgsConstructor
public class SubscriptionMapper {

    private final ObjectMapper objectMapper;

    public Subscription toEntity(SubscriptionRequestDto dto) {
        return objectMapper.convertValue(dto, Subscription.class);
    }

    public SubscriptionResponseDto toDto(Subscription subscription) {
        return objectMapper.convertValue(subscription, SubscriptionResponseDto.class);
    }
}
