package by.vyshemirski.document.service;

import by.vyshemirski.document.dto.BetHistoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TemplateService {
    private final String USER_TITLE_TEMPLATE_VARIABLE = "userTitle";
    private final String BETS_TEMPLATE_VARIABLE = "bets";
    private final String BET_HISTORY_TEMPLATE_NAME = "bet-history-template";
    private final TemplateEngine templateEngine;

    public String buildBetHistoryTemplate(String userTitle, List<BetHistoryDto> betDtoList) {
        Context context = new Context();
        context.setVariable(USER_TITLE_TEMPLATE_VARIABLE, userTitle);
        context.setVariable(BETS_TEMPLATE_VARIABLE, betDtoList);

        return templateEngine.process(BET_HISTORY_TEMPLATE_NAME, context);
    }

}
