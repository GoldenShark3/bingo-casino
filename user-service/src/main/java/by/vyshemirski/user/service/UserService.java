package by.vyshemirski.user.service;

import by.vyshemirski.user.dto.UserDto;
import by.vyshemirski.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Mono<UserDto> login(Long userId) {

        return userRepository.findById(userId)
                .map(user ->
                        UserDto.builder()
                                .userId(user.getId())
                                .title(user.getTitle())
                                .balance(BigDecimal.ZERO)
                                .lastBetId(UUID.randomUUID())
                                .build()
                );
    }

}
