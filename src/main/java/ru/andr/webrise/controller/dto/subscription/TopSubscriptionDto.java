package ru.andr.webrise.controller.dto.subscription;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TopSubscriptionDto {
    private String subscriptionName;
    private long numberOfSubscribers;
}
