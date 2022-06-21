package by.vyshemirski.invoice.controller;

import by.vyshemirski.invoice.dto.DepositMoneyDto;
import by.vyshemirski.invoice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}/last-bet")
    public Mono<UUID> retrieveLastUserBetId(@PathVariable Long userId) {

        return userService.retrieveLastUserBetId(userId);
    }

    @PostMapping("/{userId}/money")
    public Mono<DepositMoneyDto> depositBonusMoney(@PathVariable Long userId) {

        return userService.depositBonusMoney(userId);
    }

    @GetMapping("/{userId}/money")
    public Mono<Boolean> withdrawMoney(@PathVariable Long userId, @RequestBody BigDecimal moneyAmount) {

        return userService.withdrawMoney(userId, moneyAmount);
    }

    @GetMapping("/{userId}/bets/pdf")
    public Mono<byte[]> generateBetHistoryPdf(@PathVariable Long userId) {

        return userService.generateBetHistoryPdf(userId);
    }
}
