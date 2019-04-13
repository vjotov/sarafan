package com.jotov.sarafan.repo;

import com.jotov.sarafan.domain.User;
import com.jotov.sarafan.domain.UserSubscription;
import com.jotov.sarafan.domain.UserSubscriptionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSubscriptionRepo extends JpaRepository<UserSubscription, UserSubscriptionId> {
    List<UserSubscription> findBySubscriber(User user);
}
