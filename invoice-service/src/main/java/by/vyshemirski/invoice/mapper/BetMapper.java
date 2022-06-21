package by.vyshemirski.invoice.mapper;

import by.vyshemirski.invoice.dto.BetDto;
import by.vyshemirski.invoice.dto.history.BetHistoryDto;
import by.vyshemirski.invoice.model.Bet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class BetMapper {

    public BetDto toDto(Bet bet) {

        return BetDto.builder()
                .userId(bet.getUserId())
                .betId(bet.getId())
                .previousBetId(bet.getPreviousBetId())
                .betTime(bet.getCreatedAt())
                .moneyDelta(bet.getMoneyDelta())
                .build();
    }

    public BetHistoryDto toHistoryDto(Bet bet, Boolean isLegal, BigDecimal balanceAtBet) {

        return BetHistoryDto.builder()
                .userId(bet.getUserId())
                .betId(bet.getId())
                .previousBetId(bet.getPreviousBetId())
                .betTime(bet.getCreatedAt())
                .moneyDelta(bet.getMoneyDelta())
                .isLegal(isLegal)
                .balanceAtBet(balanceAtBet)
                .build();
    }

    public Bet toEntity(BetDto betDto) {

        return Bet.builder()
                .id(betDto.getBetId())
                .createdAt(betDto.getBetTime())
                .userId(betDto.getUserId())
                .previousBetId(betDto.getPreviousBetId())
                .moneyDelta(betDto.getMoneyDelta())
                .build();
    }
}
