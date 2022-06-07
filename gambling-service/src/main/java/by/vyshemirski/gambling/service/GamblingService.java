package by.vyshemirski.gambling.service;

import by.vyshemirski.gambling.dto.BetDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GamblingService {

    @Value("${app.loss_probability}")
    private Double lossProbability;

    public boolean makeBet(BetDto betDto) {
        UUID betId = UUID.randomUUID();
        boolean isUserWin = Math.random() > lossProbability;

        //todo: send message through rabbit

        return isUserWin;
    }

}
