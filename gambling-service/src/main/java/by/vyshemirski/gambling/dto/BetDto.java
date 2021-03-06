package by.vyshemirski.gambling.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BetDto {
    private Long userId;
    private BigDecimal money;
    private UUID previousBetId;
}
