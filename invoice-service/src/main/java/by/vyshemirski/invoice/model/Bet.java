package by.vyshemirski.invoice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "bets")
@Data
public class Bet {

    @Id
    private UUID id;

    private UUID previousBetId;
    private LocalDateTime createdAt;
    private Long userId;
    private BigDecimal moneyDelta;
}
