package by.vyshemirski.invoice.service;

import by.vyshemirski.invoice.model.Bet;
import by.vyshemirski.invoice.repository.BetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final BetRepository betRepository;


//    TODO: implement method
    public Mono<Double> calculateTotalBalance() {

        return null;
    }
    public Mono<Double> calculateUserBalance(Long userId) {

        return betRepository.calculateUserBalance(userId);
    }

    public Mono<UUID> retrieveLastUserBetId(Long userId) {

        return betRepository.findBetByUserIdOrderByCreatedAtDesc(userId)
                .take(1)
                .next()
                .map(Bet::getId);
    }
}
