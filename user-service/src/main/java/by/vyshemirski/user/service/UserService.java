package by.vyshemirski.user.service;

import by.vyshemirski.user.dto.DepositMoneyDto;
import by.vyshemirski.user.dto.UserDto;
import by.vyshemirski.user.model.User;
import by.vyshemirski.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final WebClient webClient;

    public Mono<UserDto> login(Mono<Long> userId) {
        Mono<User> foundedUserMono = userRepository.findById(userId);
        log.info("LOGIN log");
        return foundedUserMono
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.FORBIDDEN)))
                .flatMap(foundedUser ->
                        Mono.zip(retrieveUserBalance(foundedUser.getId()), retrieveLastUserBet(foundedUser.getId()))
                                .map(userInfo -> UserDto.builder()
                                        .userId(foundedUser.getId())
                                        .title(foundedUser.getTitle())
                                        .balance(BigDecimal.valueOf(userInfo.getT1()))
                                        .lastBetId(userInfo.getT2())
                                        .build())
                );
    }

    public Mono<UserDto> register(Mono<UserDto> userDtoMono) {
        return userDtoMono.flatMap(userDto -> {
            User user = User.builder()
                    .title(userDto.getTitle())
                    .build();

            return userRepository.save(user)
                    .flatMap(savedUser -> depositMoney(savedUser.getId())
                            .map(depositMoneyDto -> UserDto.builder()
                                    .userId(savedUser.getId())
                                    .title(savedUser.getTitle())
                                    .balance(depositMoneyDto.getDepositAmount())
                                    .lastBetId(depositMoneyDto.getDepositId())
                                    .build()
                            )
                    );
        });
    }

    private Mono<Double> retrieveUserBalance(Long userId) {

        return webClient.get()
                .uri("http://gateway-service/invoice-service/status/" + userId)
                .retrieve()
                .bodyToMono(Double.class);
    }

    private Mono<UUID> retrieveLastUserBet(Long userId) {

        return webClient.get()
                .uri("http://gateway-service/invoice-service/user/" + userId + "/last-bet")
                .retrieve()
                .bodyToMono(UUID.class);
    }

    private Mono<DepositMoneyDto> depositMoney(Long userId) {

        return webClient.post()
                .uri("http://gateway-service/invoice-service/user/" + userId + "/money")
                .retrieve()
                .bodyToMono(DepositMoneyDto.class);
    }
}