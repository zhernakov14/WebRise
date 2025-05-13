package ru.andr.webrise.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.andr.webrise.controller.dto.subscription.TopSubscriptionDto;
import ru.andr.webrise.service.SubscriptionService;
import java.util.List;

@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
public class CommonSubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping("/top")
    public ResponseEntity<List<TopSubscriptionDto>> getTopSubscriptions() {
        List<TopSubscriptionDto> topSubscriptions = subscriptionService.getTopSubscriptions();
        return ResponseEntity.ok(topSubscriptions);
    }
}
