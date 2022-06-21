package by.vyshemirski.document.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BetHistoryDto {
    private Long userId;
    private UUID betId;
    private UUID previousBetId;
    private BigDecimal moneyDelta;
    private LocalDateTime betTime;
    private Boolean isLegal;
    private BigDecimal balanceAtBet;
}