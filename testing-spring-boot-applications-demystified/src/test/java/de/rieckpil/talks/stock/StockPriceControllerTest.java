package de.rieckpil.talks.stock;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@WebFluxTest(StockPriceController.class)
@DisplayName("Stock Price Controller Tests")
class StockPriceControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private StockPriceService stockPriceService;

    @Test
    @DisplayName("should return stock price when ticker symbol exists")
    void shouldReturnStockPriceWhenTickerSymbolExists() {
        // Arrange
        String ticker = "AAPL";
        StockPrice stockPrice = new StockPrice(ticker, 150.25);

        when(stockPriceService.getStockPrice(ticker)).thenReturn(Mono.just(stockPrice));

        // Act & Assert
        webTestClient.get()
                .uri("/api/stocks/{ticker}", ticker)
                .exchange()
                .expectStatus().isOk()
                .expectBody(StockPrice.class)
                .isEqualTo(stockPrice);
    }

    @Test
    @DisplayName("should return 404 when ticker symbol is not found")
    void shouldReturn404WhenTickerSymbolIsNotFound() {
        // Arrange
        String ticker = "INVALID";

        when(stockPriceService.getStockPrice(ticker)).thenReturn(Mono.empty());

        // Act & Assert
        webTestClient.get()
                .uri("/api/stocks/{ticker}", ticker)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @DisplayName("should return 400 when ticker symbol is invalid format")
    void shouldReturn400WhenTickerSymbolIsInvalidFormat() {
        // Arrange
        String ticker = " ";

        // Act & Assert
        webTestClient.get()
                .uri("/api/stocks/{ticker}", ticker)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    @DisplayName("should return 500 when service throws exception")
    void shouldReturn500WhenServiceThrowsException() {
        // Arrange
        String ticker = "AAPL";

        when(stockPriceService.getStockPrice(anyString())).thenReturn(Mono.error(new RuntimeException("Service error")));

        // Act & Assert
        webTestClient.get()
                .uri("/api/stocks/{ticker}", ticker)
                .exchange()
                .expectStatus().is5xxServerError();
    }
}