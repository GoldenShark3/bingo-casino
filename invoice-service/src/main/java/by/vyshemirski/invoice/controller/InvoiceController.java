package by.vyshemirski.invoice.controller;

import by.vyshemirski.invoice.dto.DepositMoneyDto;
import by.vyshemirski.invoice.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping("/status")
    public Mono<Double> calculateTotalBalance() {

        return invoiceService.calculateTotalBalance();
    }

    @GetMapping("/status/{userId}")
    public Mono<Double> calculateUserBalance(@PathVariable Long userId) {

        return invoiceService.calculateUserBalance(userId);
    }

    @GetMapping("/user/{userId}/last-bet")
    public Mono<UUID> retrieveLastUserBetId(@PathVariable Long userId) {

        return invoiceService.retrieveLastUserBetId(userId);
    }

    @PostMapping("/user/{userId}/money")
    public Mono<DepositMoneyDto> depositMoney(@PathVariable Long userId) {

        return invoiceService.depositMoney(userId);
    }

    @GetMapping("/user/{userId}/money")
    public Mono<Boolean> withdrawMoney(@PathVariable Long userId, @RequestBody BigDecimal moneyAmount) {

        return invoiceService.withdrawMoney(userId, moneyAmount);
    }

}
