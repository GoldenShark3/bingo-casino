package by.vyshemirski.user.service;

import by.vyshemirski.user.dto.DepositMoneyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WebClientService {

    private final WebClient webClient;

    public Mono<Double> retrieveUserBalance(Long userId) {

        return webClient.get()
                .uri("http://gateway-service/invoice-service/status/" + userId)
                .retrieve()
                .bodyToMono(Double.class);
    }

    public Mono<UUID> retrieveLastUserBet(Long userId) {

        return webClient.get()
                .uri("http://gateway-service/invoice-service/user/" + userId + "/last-bet")
                .retrieve()
                .bodyToMono(UUID.class);
    }

    public Mono<DepositMoneyDto> depositMoney(Long userId) {

        return webClient.post()
                .uri("http://gateway-service/invoice-service/user/" + userId + "/money")
                .retrieve()
                .bodyToMono(DepositMoneyDto.class);
    }

}
