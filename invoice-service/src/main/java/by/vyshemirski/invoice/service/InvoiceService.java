package by.vyshemirski.invoice.service;

import by.vyshemirski.invoice.dto.DepositMoneyDto;
import by.vyshemirski.invoice.model.Bet;
import by.vyshemirski.invoice.repository.BetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final BetRepository betRepository;

    public Mono<Bet> save(Bet bet) {

        return betRepository.save(bet);
    }

    public Mono<Double> calculateTotalBalance() {

        return betRepository.calculateTotalBalance();
    }

    public Mono<Double> calculateUserBalance(Long userId) {

        return betRepository.calculateUserBalance(userId);
    }

    public Mono<UUID> retrieveLastUserBetId(Long userId) {

        return betRepository.findTopBetByUserIdOrderByCreatedAtDesc(userId).map(Bet::getId);
    }

    public Mono<DepositMoneyDto> depositMoney(Long userId) {
        Bet bet = Bet.builder()
                .id(UUID.randomUUID())
                .userId(userId)
                .moneyDelta(BigDecimal.valueOf(400))
                .createdAt(LocalDateTime.now())
                .build();

        return betRepository.save(bet)
                .map(savedBet ->
                        DepositMoneyDto.builder()
                                .depositId(savedBet.getId())
                                .depositAmount(savedBet.getMoneyDelta())
                                .build()
                );
    }
}
