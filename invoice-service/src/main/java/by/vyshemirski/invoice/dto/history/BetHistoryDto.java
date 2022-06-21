package by.vyshemirski.invoice.dto.history;

import by.vyshemirski.invoice.dto.BetDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BetHistoryDto extends BetDto {

    private Boolean isLegal;
    private BigDecimal balanceAtBet;
}
