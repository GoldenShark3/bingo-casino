package by.vyshemirski.invoice.repository;

import by.vyshemirski.invoice.model.Bet;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface BetRepository extends ReactiveCrudRepository<Bet, UUID> {

    @Query("select sum(money_delta) from bets where user_id = :userId")
    Mono<Double> calculateUserBalance(@Param("userId") Long userId);

    Mono<Bet> findTopBetByUserIdOrderByCreatedAtDesc(Long userId);

    @Query("select sum(money_delta) from bets")
    Mono<Double> calculateTotalBalance();
}
