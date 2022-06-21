package by.vyshemirski.document.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BetHistoryListDto {
    private String userTitle;
    private List<BetHistoryDto> bets;
}