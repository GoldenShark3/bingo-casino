package by.vyshemirski.document.service;

import by.vyshemirski.document.dto.BetHistoryListDto;
import com.lowagie.text.DocumentException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.xhtmlrenderer.pdf.ITextRenderer;
import reactor.core.publisher.Mono;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class PdfService {

    private final TemplateService templateService;

    public Mono<byte[]> buildBetHistoryPdf(BetHistoryListDto betHistoryListDto) {
        byte[] pdfBytes;
        String processedTemplate = templateService.buildBetHistoryTemplate(betHistoryListDto.getUserTitle(), betHistoryListDto.getBets());

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(processedTemplate);
            renderer.layout();
            renderer.createPDF(outputStream);
            pdfBytes = outputStream.toByteArray();
        } catch (IOException | DocumentException e) {
            log.error("Something went wrong while building pdf. Message - {}", e.getMessage());
            return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST));
        }

        return Mono.just(pdfBytes);

    }

}
