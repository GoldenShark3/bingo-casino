package by.vyshemirski.document.controller;

import by.vyshemirski.document.dto.BetHistoryListDto;
import by.vyshemirski.document.service.PdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class DocumentController {

    private final PdfService pdfService;

    @PostMapping(value = "/bet-history/pdf")
    public Mono<byte[]> retrieveBetHistoryPdf(@RequestBody BetHistoryListDto betHistoryListDto) {

        return pdfService.buildBetHistoryPdf(betHistoryListDto);
    }

}
