package by.vyshemirski.gambling.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BetResultDto {
    private Boolean isUserWin;
    private UUID betId;
}
