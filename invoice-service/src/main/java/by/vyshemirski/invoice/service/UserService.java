package by.vyshemirski.invoice.service;

import by.vyshemirski.invoice.dto.history.BetHistoryListDto;
import by.vyshemirski.invoice.dto.DepositMoneyDto;
import by.vyshemirski.invoice.model.Bet;
import by.vyshemirski.invoice.repository.BetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final BetService betService;
    private final WebClientService webClientService;
    private final BetRepository betRepository;

    public Mono<UUID> retrieveLastUserBetId(Long userId) {

        return betRepository.findTopBetByUserIdOrderByCreatedAtDesc(userId).map(Bet::getId);
    }

    public Mono<DepositMoneyDto> depositBonusMoney(Long userId) {
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
                        boolean isBetLegal = betService.isBetLegal(bets.get(i), bets.get(i - 1), userBalance);

                        if (isBetLegal) {
                            userBalance = userBalance.add(bets.get(i).getMoneyDelta());
                        } else {
                            return false;
                        }
                    }
                    return userBalance.compareTo(moneyAmount) >= 0;
                });
    }


    public Mono<byte[]> generateBetHistoryPdf(Long userId) {
        Mono<String> userTitleMono = webClientService.retrieveUserTitleById(userId);
        Mono<List<Bet>> betsListMono = betRepository.findAllByUserIdOrderByCreatedAt(userId).collectList();

        return Mono.zip(userTitleMono, betsListMono)
                .map(betsInfo -> BetHistoryListDto.builder()
                        .userTitle(betsInfo.getT1())
                        .bets(betService.checkAllBetsForLegitimacyAndTransferToDto(betsInfo.getT2()))
                        .build())
                .flatMap(webClientService::buildBetHistoryPdfFile);
    }
}
