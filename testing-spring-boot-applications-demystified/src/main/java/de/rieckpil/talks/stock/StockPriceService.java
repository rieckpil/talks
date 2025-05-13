package de.rieckpil.talks.stock;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class StockPriceService {

    private final WebClient.Builder webClientBuilder;
    private final String baseUrl;

    public StockPriceService(WebClient.Builder webClientBuilder, 
                             @Value("${stock.api.base-url:https://api.example.com}") String baseUrl) {
        this.webClientBuilder = webClientBuilder;
        this.baseUrl = baseUrl;
    }

    public Mono<StockPrice> getStockPrice(String ticker) {
        return webClientBuilder
                .baseUrl(baseUrl)
                .build()
                .get()
                .uri("/api/v1/stocks/{ticker}", ticker)
                .retrieve()
                .bodyToMono(StockPriceResponse.class)
                .map(response -> new StockPrice(response.symbol(), response.price()))
                .onErrorResume(WebClientResponseException.class, ex -> {
                    if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.empty();
                    }
                    return Mono.error(ex);
                });
    }
}