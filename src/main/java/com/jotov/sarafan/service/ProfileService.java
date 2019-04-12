package com.jotov.sarafan.service;

import com.jotov.sarafan.domain.User;
import com.jotov.sarafan.domain.UserSubscription;
import com.jotov.sarafan.repo.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProfileService {
    private final UserDetailsRepo userDetailsRepo;

    @Autowired
    public ProfileService(UserDetailsRepo userDetailsRepo) {
        this.userDetailsRepo = userDetailsRepo;
    }

    public User changeSubscription(User channel, User subscriber) {
        List<UserSubscription> subscribers = channel.getSubscribers()
                .stream()
                .filter(subscribtion ->
                        subscriber.getSubscribers().equals(subscriber)
                )
                .collect(Collectors.toList());

        if( subscribers.isEmpty()) {
            UserSubscription subscription = new UserSubscription(channel, subscriber);
            channel.getSubscribers().add(subscription);
        } else {
            channel.getSubscribers().removeAll(subscribers);
        }
        return userDetailsRepo.save(channel);
    }
}
