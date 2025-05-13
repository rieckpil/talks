package de.rieckpil.talks.stock;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Stock Price Service Tests")
class StockPriceServiceTest {

    @Mock
    private WebClient webClient;
    
    @Mock
    private WebClient.Builder webClientBuilder;
    
    @Mock
    private RequestHeadersUriSpec<?> requestHeadersUriSpec;
    
    @Mock
    private RequestHeadersSpec<?> requestHeadersSpec;
    
    @Mock
    private ResponseSpec responseSpec;
    
    @InjectMocks
    private StockPriceService cut;
    
    @Nested
    @DisplayName("getStockPrice method tests")
    class GetStockPriceTests {
        
        @Test
        @DisplayName("should return stock price when ticker symbol is valid")
        void shouldReturnStockPriceWhenTickerSymbolIsValid() {
            // Arrange
            String ticker = "AAPL";
            StockPriceResponse expectedResponse = new StockPriceResponse(ticker, 150.25);
            
            when(webClientBuilder.baseUrl(anyString())).thenReturn(webClientBuilder);
            when(webClientBuilder.build()).thenReturn(webClient);
            when(webClient.get()).thenReturn(requestHeadersUriSpec);
            when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
            when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
            when(responseSpec.bodyToMono(StockPriceResponse.class)).thenReturn(Mono.just(expectedResponse));
            
            // Act
            Mono<StockPrice> result = cut.getStockPrice(ticker);
            
            // Assert
            StepVerifier.create(result)
                .expectNext(new StockPrice(ticker, 150.25))
                .verifyComplete();
        }
        
        @Test
        @DisplayName("should return empty when ticker symbol is not found")
        void shouldReturnEmptyWhenTickerSymbolIsNotFound() {
            // Arrange
            String ticker = "INVALID";
            
            when(webClientBuilder.baseUrl(anyString())).thenReturn(webClientBuilder);
            when(webClientBuilder.build()).thenReturn(webClient);
            when(webClient.get()).thenReturn(requestHeadersUriSpec);
            when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
            when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
            when(responseSpec.bodyToMono(StockPriceResponse.class)).thenReturn(Mono.empty());
            
            // Act
            Mono<StockPrice> result = cut.getStockPrice(ticker);
            
            // Assert
            StepVerifier.create(result)
                .verifyComplete();
        }
        
        @Test
        @DisplayName("should handle error when API request fails")
        void shouldHandleErrorWhenApiRequestFails() {
            // Arrange
            String ticker = "AAPL";
            
            when(webClientBuilder.baseUrl(anyString())).thenReturn(webClientBuilder);
            when(webClientBuilder.build()).thenReturn(webClient);
            when(webClient.get()).thenReturn(requestHeadersUriSpec);
            when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
            when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
            when(responseSpec.bodyToMono(StockPriceResponse.class)).thenReturn(Mono.error(new RuntimeException("API Error")));
            
            // Act
            Mono<StockPrice> result = cut.getStockPrice(ticker);
            
            // Assert
            StepVerifier.create(result)
                .expectError(RuntimeException.class)
                .verify();
        }
    }
}