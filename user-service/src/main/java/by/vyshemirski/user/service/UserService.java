package by.vyshemirski.user.service;

import by.vyshemirski.user.dto.UserDto;
import by.vyshemirski.user.model.User;
import by.vyshemirski.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final WebClientService webClientService;

    public Mono<UserDto> login(Mono<Long> userId) {
        Mono<User> foundedUserMono = userRepository.findById(userId);

        return foundedUserMono
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.FORBIDDEN)))
                .flatMap(foundedUser ->
                        Mono.zip(webClientService.retrieveUserBalance(foundedUser.getId()), webClientService.retrieveLastUserBet(foundedUser.getId()))
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
                    .flatMap(savedUser -> webClientService.depositMoney(savedUser.getId())
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

    public Mono<String> findUserTitleById(Long userId) {

        return userRepository.findById(userId)
                .map(User::getTitle)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }


}