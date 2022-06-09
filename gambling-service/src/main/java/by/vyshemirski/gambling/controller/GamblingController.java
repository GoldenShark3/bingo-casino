package by.vyshemirski.gambling.controller;

import by.vyshemirski.gambling.dto.BetDto;
import by.vyshemirski.gambling.dto.BetResultDto;
import by.vyshemirski.gambling.service.GamblingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class GamblingController {
    private final GamblingService gamblingService;

    @PostMapping("/bet")
    public Mono<BetResultDto> makeBet(@RequestBody Mono<BetDto> betDto) {

        return gamblingService.makeBet(betDto);
    }

}
