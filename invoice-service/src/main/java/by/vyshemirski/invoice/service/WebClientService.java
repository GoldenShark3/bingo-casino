package by.vyshemirski.invoice.service;

import by.vyshemirski.invoice.dto.history.BetHistoryListDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebClientService {
    private final WebClient webClient;

    public Mono<String> retrieveUserTitleById(Long userId) {

        return webClient.get()
                .uri("http://gateway-service/user-service/title/" + userId)
                .retrieve()
                .bodyToMono(String.class)
                .doOnError((error) -> log.error("Cannot get user title with id = {}. Error - {}", userId, error.getMessage()));
    }

    public Mono<byte[]> buildBetHistoryPdfFile(BetHistoryListDto betHistoryListDto) {

        return webClient.post()
                .uri("http://gateway-service/document-service/bet-history/pdf")
                .body(Mono.just(betHistoryListDto), BetHistoryListDto.class)
                .retrieve()
                .bodyToMono(byte[].class)
                .doOnError(error -> log.error("Error while building bet history pdf file. Message - {}", error.getMessage()));
    }

}
