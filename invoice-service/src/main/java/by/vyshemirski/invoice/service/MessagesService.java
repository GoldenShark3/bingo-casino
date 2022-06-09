package by.vyshemirski.invoice.service;

import by.vyshemirski.invoice.dto.BetMessageDto;
import by.vyshemirski.invoice.model.Bet;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class MessagesService {

    private final InvoiceService invoiceService;

    @Bean
    public Consumer<BetMessageDto> gamblingServiceMessage() {

        return (message -> {
            Bet bet = Bet.builder()
                    .id(message.getBetId())
                    .createdAt(message.getBetTime())
                    .moneyDelta(message.getMoneyDelta())
                    .previousBetId(message.getPreviousBetId())
                    .userId(message.getUserId())
                    .build();

            invoiceService.save(bet).subscribe(System.out::println);
        });
    }

}
