package de.rieckpil.talks.stock;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Stock Price Service Tests")
class StockPriceServiceTest {

    @Mock
    private WebClient.Builder webClientBuilder;
    
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
            String baseUrl = "https://api.example.com";
            StockPriceResponse expectedResponse = new StockPriceResponse(ticker, 150.25);
            
            // Manually set the baseUrl field using reflection
            ReflectionTestUtils.setField(cut, "baseUrl", baseUrl);
            
            // Setup WebClient mock chain
            WebClient mockWebClient = mock(WebClient.class);
            WebClient.RequestHeadersUriSpec mockUriSpec = mock(WebClient.RequestHeadersUriSpec.class);
            WebClient.RequestHeadersSpec mockHeadersSpec = mock(WebClient.RequestHeadersSpec.class);
            WebClient.ResponseSpec mockResponseSpec = mock(WebClient.ResponseSpec.class);
            
            when(webClientBuilder.baseUrl(eq(baseUrl))).thenReturn(webClientBuilder);
            when(webClientBuilder.build()).thenReturn(mockWebClient);
            when(mockWebClient.get()).thenReturn(mockUriSpec);
            when(mockUriSpec.uri(anyString(), eq(ticker))).thenReturn(mockHeadersSpec);
            when(mockHeadersSpec.retrieve()).thenReturn(mockResponseSpec);
            when(mockResponseSpec.bodyToMono(StockPriceResponse.class)).thenReturn(Mono.just(expectedResponse));
            
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
            String baseUrl = "https://api.example.com";
            
            // Manually set the baseUrl field using reflection
            ReflectionTestUtils.setField(cut, "baseUrl", baseUrl);
            
            // Setup WebClient mock chain
            WebClient mockWebClient = mock(WebClient.class);
            WebClient.RequestHeadersUriSpec mockUriSpec = mock(WebClient.RequestHeadersUriSpec.class);
            WebClient.RequestHeadersSpec mockHeadersSpec = mock(WebClient.RequestHeadersSpec.class);
            WebClient.ResponseSpec mockResponseSpec = mock(WebClient.ResponseSpec.class);
            
            when(webClientBuilder.baseUrl(eq(baseUrl))).thenReturn(webClientBuilder);
            when(webClientBuilder.build()).thenReturn(mockWebClient);
            when(mockWebClient.get()).thenReturn(mockUriSpec);
            when(mockUriSpec.uri(anyString(), eq(ticker))).thenReturn(mockHeadersSpec);
            when(mockHeadersSpec.retrieve()).thenReturn(mockResponseSpec);
            when(mockResponseSpec.bodyToMono(StockPriceResponse.class)).thenReturn(Mono.empty());
            
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
            String baseUrl = "https://api.example.com";
            
            // Manually set the baseUrl field using reflection
            ReflectionTestUtils.setField(cut, "baseUrl", baseUrl);
            
            // Setup WebClient mock chain
            WebClient mockWebClient = mock(WebClient.class);
            WebClient.RequestHeadersUriSpec mockUriSpec = mock(WebClient.RequestHeadersUriSpec.class);
            WebClient.RequestHeadersSpec mockHeadersSpec = mock(WebClient.RequestHeadersSpec.class);
            WebClient.ResponseSpec mockResponseSpec = mock(WebClient.ResponseSpec.class);
            
            when(webClientBuilder.baseUrl(eq(baseUrl))).thenReturn(webClientBuilder);
            when(webClientBuilder.build()).thenReturn(mockWebClient);
            when(mockWebClient.get()).thenReturn(mockUriSpec);
            when(mockUriSpec.uri(anyString(), eq(ticker))).thenReturn(mockHeadersSpec);
            when(mockHeadersSpec.retrieve()).thenReturn(mockResponseSpec);
            when(mockResponseSpec.bodyToMono(StockPriceResponse.class)).thenReturn(Mono.error(new RuntimeException("API Error")));
            
            // Act
            Mono<StockPrice> result = cut.getStockPrice(ticker);
            
            // Assert
            StepVerifier.create(result)
                .expectError(RuntimeException.class)
                .verify();
        }
    }
}