package by.vyshemirski.invoice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BetDto {
    private Long userId;
    private UUID betId;
    private UUID previousBetId;
    private BigDecimal moneyDelta;
    private LocalDateTime betTime;
}
