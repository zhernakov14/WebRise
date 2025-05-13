package ru.andr.webrise.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.andr.webrise.model.Subscription;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    Optional<Subscription> findBySubscriptionName(String subscriptionName);

    @Query("SELECT s FROM Subscription s LEFT JOIN s.users u GROUP BY s.id ORDER BY COUNT(u) DESC")
    List<Subscription> findTop3Popular(Pageable pageable);
}
