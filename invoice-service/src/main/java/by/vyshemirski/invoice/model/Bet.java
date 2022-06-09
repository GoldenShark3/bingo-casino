package by.vyshemirski.invoice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "bets")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Bet implements Persistable<UUID> {
    @Id
    private UUID id;
    private UUID previousBetId;
    private LocalDateTime createdAt;
    private Long userId;
    private BigDecimal moneyDelta;

    @Override
    public boolean isNew() {
        return Boolean.TRUE;
    }
}
