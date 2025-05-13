package ru.andr.webrise.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.andr.webrise.controller.dto.subscription.SubscriptionRequestDto;
import ru.andr.webrise.controller.dto.subscription.SubscriptionResponseDto;
import ru.andr.webrise.controller.dto.subscription.TopSubscriptionDto;
import ru.andr.webrise.model.Subscription;
import ru.andr.webrise.model.User;
import ru.andr.webrise.repository.SubscriptionRepository;
import ru.andr.webrise.repository.UserRepository;
import ru.andr.webrise.util.ExceptionFactory;
import ru.andr.webrise.util.SubscriptionMapper;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final SubscriptionMapper subscriptionMapper;


    @Transactional
    public void addSubscription(Long userId, SubscriptionRequestDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> ExceptionFactory.createUserEntityNotFoundException(userId));

        Subscription subscription = subscriptionRepository.findBySubscriptionName(dto.getSubscriptionName())
                .orElseGet(() -> {
                    Subscription newSubscription = new Subscription();
                    newSubscription.setSubscriptionName(dto.getSubscriptionName());
                    log.info("Создание новой подписки: {}", dto.getSubscriptionName());
                    return subscriptionRepository.save(newSubscription);
                });

        log.info("Добавление подписки {} пользователю {}", dto.getSubscriptionName(), user.getName());
        user.getSubscriptions().add(subscription);
        userRepository.save(user);
    }

    public List<SubscriptionResponseDto> getUserSubscriptions(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> ExceptionFactory.createUserEntityNotFoundException(userId));

        log.info("Получение подписок пользователя {}", user.getName());
        return user.getSubscriptions().stream().map(subscriptionMapper::toDto).toList();
    }

    @Transactional
    public void deleteSubscription(Long userId, Long subscriptionId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> ExceptionFactory.createUserEntityNotFoundException(userId));

        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> ExceptionFactory.createSubscriptionEntityNotFoundException(subscriptionId));

        if (!user.getSubscriptions().contains(subscription)) {
            throw ExceptionFactory.createNotSubscribedException(userId,subscriptionId);
        }

        log.info("Удаление подписки {} у пользователя {}", subscription.getSubscriptionName(), user.getName());
        user.getSubscriptions().remove(subscription);
        userRepository.save(user);
    }

    public List<TopSubscriptionDto> getTopSubscriptions() {
        log.info("Получение топ-3 популярных подписок");
        Pageable limit = PageRequest.of(0, 3);
        return subscriptionRepository.findTop3Popular(limit).stream()
                .map(s -> new TopSubscriptionDto(
                        s.getSubscriptionName(),
                        s.getUsers().size()))
                .toList();
    }
}
