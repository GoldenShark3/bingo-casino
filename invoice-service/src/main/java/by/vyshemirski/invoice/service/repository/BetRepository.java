package by.vyshemirski.invoice.service.repository;

import by.vyshemirski.invoice.service.model.Bet;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BetRepository extends ReactiveCrudRepository<Bet, UUID> {
}
