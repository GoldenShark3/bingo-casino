package by.vyshemirski.gambling.service;

import by.vyshemirski.gambling.dto.BetDto;
import by.vyshemirski.gambling.dto.BetMessageDto;
import by.vyshemirski.gambling.dto.BetResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GamblingService {

    private final StreamBridge streamBridge;
    @Value("${app.loss_probability}")
    private Double lossProbability;

    public Mono<BetResultDto> makeBet(Mono<BetDto> betDtoMono) {
        return betDtoMono.map(betDto -> {

            UUID betId = UUID.randomUUID();
            boolean isUserWin = Math.random() > lossProbability;

            sendMessage(betDto, isUserWin, betId);

            return BetResultDto.builder()
                    .betId(betId)
                    .isUserWin(isUserWin)
                    .build();
        });
    }

    private void sendMessage(BetDto betDto, Boolean isUserWin, UUID betId) {
        BetMessageDto betResult = BetMessageDto.builder()
                .betId(betId)
                .betTime(LocalDateTime.now())
                .previousBetId(betDto.getPreviousBetId())
                .userId(betDto.getUserId())
                .moneyDelta(isUserWin ? betDto.getMoney() : betDto.getMoney().negate())
                .build();

        streamBridge.send("invoice-service", betResult);
    }
}