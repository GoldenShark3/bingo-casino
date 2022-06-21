package by.vyshemirski.invoice.service;

import by.vyshemirski.invoice.dto.BetDto;
import by.vyshemirski.invoice.dto.history.BetHistoryDto;
import by.vyshemirski.invoice.dto.history.BetHistoryListDto;
import by.vyshemirski.invoice.mapper.BetMapper;
import by.vyshemirski.invoice.model.Bet;
import by.vyshemirski.invoice.repository.BetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BetService {

    private final BetMapper betMapper;
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

    public boolean isBetLegal(Bet currentBet, Bet previousBet, BigDecimal userBalanceAtBet) {

        if (!currentBet.getPreviousBetId().equals(previousBet.getId())) {
            return false;
        }

        return userBalanceAtBet.compareTo(currentBet.getMoneyDelta().abs()) >= 0;
    }

    List<BetHistoryDto> checkAllBetsForLegitimacyAndTransferToDto(List<Bet> bets) {
        BetHistoryDto startUserBonus = betMapper.toHistoryDto(bets.get(0), true, BigDecimal.ZERO);
        List<BetHistoryDto> betDtoList = new ArrayList<>(List.of(startUserBonus));

        BigDecimal userBalance = bets.get(0).getMoneyDelta();

        for (int i = 1; i < bets.size(); i++) {
            Bet currentBet = bets.get(i);
            Bet previousBet = bets.get(i - 1);

            boolean isBetLegal = isBetLegal(currentBet, previousBet, userBalance);
            BetHistoryDto betDto = betMapper.toHistoryDto(currentBet, isBetLegal, userBalance);
            betDtoList.add(betDto);

            userBalance = userBalance.add(currentBet.getMoneyDelta());
        }

        return betDtoList;
    }

}
