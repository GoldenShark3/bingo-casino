package by.vyshemirski.user.router;

import by.vyshemirski.user.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> route(UserHandler userHandler) {

        return RouterFunctions.route(
                RequestPredicates.POST("/login").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                userHandler::login
        ).andRoute(
                RequestPredicates.POST("/register").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                userHandler::register
        ).andRoute(
                RequestPredicates.GET("/title/{userId}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                userHandler::retrieveUserTitleById
        );
    }
}
