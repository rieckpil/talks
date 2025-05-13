package de.rieckpil.talks.stock;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/stocks")
public class StockPriceController {

    private final StockPriceService stockPriceService;

    public StockPriceController(StockPriceService stockPriceService) {
        this.stockPriceService = stockPriceService;
    }

    @GetMapping("/{ticker}")
    public Mono<ResponseEntity<StockPrice>> getStockPrice(@PathVariable String ticker) {
        if (ticker == null || ticker.isBlank()) {
            return Mono.just(ResponseEntity.badRequest().build());
        }

        return stockPriceService.getStockPrice(ticker)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }
}