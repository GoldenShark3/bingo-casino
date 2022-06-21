package by.vyshemirski.invoice.service;

import by.vyshemirski.invoice.dto.BetDto;
import by.vyshemirski.invoice.mapper.BetMapper;
import by.vyshemirski.invoice.model.Bet;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class MessagesService {

    private final BetService betService;
    private final BetMapper betMapper;

    @Bean
    public Consumer<BetDto> gamblingServiceMessage() {

        return (message -> {
            Bet bet = betMapper.toEntity(message);
            betService.save(bet).subscribe(System.out::println);
        });
    }

}
