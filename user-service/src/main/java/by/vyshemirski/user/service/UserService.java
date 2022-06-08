package by.vyshemirski.user.service;

import by.vyshemirski.user.dto.UserDto;
import by.vyshemirski.user.model.User;
import by.vyshemirski.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import javax.ws.rs.core.UriBuilder;
import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final WebClient webClient;

    public Mono<UserDto> login(Mono<Long> userId) {

        return userRepository.findById(userId).flatMap(foundedUser ->
                Mono.zip(retrieveUserBalance(foundedUser.getId()), retrieveLastUserBet(foundedUser.getId()))
                        .map(userInfo -> UserDto.builder()
                                .userId(foundedUser.getId())
                                .title(foundedUser.getTitle())
                                .balance(BigDecimal.valueOf(userInfo.getT1()))
                                .lastBetId(userInfo.getT2())
                                .build())
        );
    }

    private Mono<Double> retrieveUserBalance(Long userId) {

        return webClient.get()
                .uri("http://invoice-service:8000/status/" + userId)
                .retrieve()
                .bodyToMono(Double.class);
    }

    private Mono<UUID> retrieveLastUserBet(Long userId) {

        return webClient.get()
                .uri("http://invoice-service:8000/user/" + userId + "/last-bet")
                .retrieve()
                .bodyToMono(UUID.class);
    }
}
