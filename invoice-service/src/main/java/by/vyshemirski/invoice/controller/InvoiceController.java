package by.vyshemirski.invoice.controller;

import by.vyshemirski.invoice.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping("/status")
    public Mono<Double> calculateTotalBalance() {

        return null;
    }

    @GetMapping("/status/{userId}")
    public Mono<Double> calculateUserBalance(@PathVariable Long userId) {
        return invoiceService.calculateUserBalance(userId);
    }

    @GetMapping("/user/{userId}/last-bet")
    public Mono<UUID> retrieveLastUserBetId(@PathVariable Long userId) {

        return invoiceService.retrieveLastUserBetId(userId);
    }

}
