package ru.andr.webrise.util;

import jakarta.persistence.EntityNotFoundException;

public class ExceptionFactory {

    private static final String USER_NOT_FOUND = "Пользователь с ID %d не найден";
    private static final String SUBSCRIPTION_NOT_FOUND = "Подписка с ID %d не найдена";
    private static final String USER_IS_NOT_SUBSCRIBED = "Пользователь с ID %d не имеет подписки с ID %d";

    public static EntityNotFoundException createUserEntityNotFoundException(Long userId) {
        return new EntityNotFoundException(USER_NOT_FOUND.formatted(userId));
    }

    public static EntityNotFoundException createSubscriptionEntityNotFoundException(Long subscriptionId) {
        return new EntityNotFoundException(SUBSCRIPTION_NOT_FOUND.formatted(subscriptionId));
    }

    public static EntityNotFoundException createNotSubscribedException(Long userId, Long subscriptionId) {
        return new EntityNotFoundException(USER_IS_NOT_SUBSCRIBED.formatted(userId, subscriptionId));
    }
}
