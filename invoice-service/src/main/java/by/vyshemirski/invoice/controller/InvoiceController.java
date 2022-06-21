package by.vyshemirski.invoice.controller;

import by.vyshemirski.invoice.service.BetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class InvoiceController {

    private final BetService betService;

    @GetMapping("/status")
    public Mono<Double> calculateTotalBalance() {

        return betService.calculateTotalBalance();
    }

    @GetMapping("/status/{userId}")
    public Mono<Double> calculateUserBalance(@PathVariable Long userId) {

        return betService.calculateUserBalance(userId);
    }
}
