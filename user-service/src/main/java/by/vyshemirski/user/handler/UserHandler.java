package by.vyshemirski.user.handler;

import by.vyshemirski.user.dto.UserDto;
import by.vyshemirski.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserHandler {

    private final UserService userService;

    public Mono<ServerResponse> login(ServerRequest request) {

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(
                        userService.login(request.bodyToMono(Long.class)),
                        UserDto.class
                );
    }

    public Mono<ServerResponse> register(ServerRequest request) {

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        userService.register(request.bodyToMono(UserDto.class)),
                        UserDto.class
                );
    }

    public Mono<ServerResponse> retrieveUserTitleById(ServerRequest request) {

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        userService.findUserTitleById(Long.parseLong(request.pathVariable("userId"))),
                        String.class
                );
    }
}
