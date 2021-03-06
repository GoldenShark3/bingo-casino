package by.vyshemirski.user.repository;

import by.vyshemirski.user.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {
}