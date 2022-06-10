package by.vyshemirski.invoice.service;

import by.vyshemirski.invoice.dto.DepositMoneyDto;
import by.vyshemirski.invoice.model.Bet;
import by.vyshemirski.invoice.repository.BetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    public Mono<Boolean> withdrawMoney(Long userId, BigDecimal moneyAmount) {

        return betRepository.findAllByUserIdOrderByCreatedAt(userId)
                .collectList()
                .map(bets -> {
                    BigDecimal userBalance = bets.get(0).getMoneyDelta();
                    for (int i = 1; i < bets.size(); i++) {

                        if (!bets.get(i).getPreviousBetId().equals(bets.get(i - 1).getId())) {
                            return false;
                        }

                        if (userBalance.compareTo(bets.get(i).getMoneyDelta()) < 0) {
                            return false;
                        }

                        userBalance = userBalance.add(bets.get(i).getMoneyDelta());

                        if (bets.size() == i + 1 && userBalance.compareTo(moneyAmount) < 0) {
                            return false;
                        }
                    }
                    return true;
                });
    }
}
