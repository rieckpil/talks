package de.rieckpil.talks.stock;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Stock Price API Integration Tests")
class StockPriceApiIntegrationTest {

    @RegisterExtension
    static WireMockExtension wireMock = WireMockExtension.newInstance()
            .options(wireMockConfig().dynamicPort())
            .build();

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("stock.api.base-url", wireMock::baseUrl);
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        public WebClient.Builder webClientBuilder() {
            return WebClient.builder();
        }
    }

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("should successfully retrieve stock price from API")
    void shouldSuccessfullyRetrieveStockPriceFromApi() {
        // Arrange
        String ticker = "AAPL";
        String responseBody = """
                {
                  "symbol": "AAPL",
                  "price": 150.25
                }
                """;

        wireMock.stubFor(
                get(urlPathMatching("/api/v1/stocks/" + ticker))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .withBody(responseBody))
        );

        // Act & Assert
        webTestClient.get()
                .uri("/api/stocks/{ticker}", ticker)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.symbol").isEqualTo(ticker)
                .jsonPath("$.price").isEqualTo(150.25);
    }

    @Test
    @DisplayName("should return 404 when stock does not exist")
    void shouldReturn404WhenStockDoesNotExist() {
        // Arrange
        String ticker = "INVALID";

        wireMock.stubFor(
                get(urlPathMatching("/api/v1/stocks/" + ticker))
                        .willReturn(aResponse()
                                .withStatus(404)
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .withBody("{}"))
        );

        // Act & Assert
        webTestClient.get()
                .uri("/api/stocks/{ticker}", ticker)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @DisplayName("should return 500 when external API fails")
    void shouldReturn500WhenExternalApiFails() {
        // Arrange
        String ticker = "ERROR";

        wireMock.stubFor(
                get(urlPathMatching("/api/v1/stocks/" + ticker))
                        .willReturn(aResponse()
                                .withStatus(500))
        );

        // Act & Assert
        webTestClient.get()
                .uri("/api/stocks/{ticker}", ticker)
                .exchange()
                .expectStatus().is5xxServerError();
    }
}
