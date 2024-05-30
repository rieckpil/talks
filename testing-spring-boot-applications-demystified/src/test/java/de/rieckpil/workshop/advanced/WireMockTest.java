package de.rieckpil.workshop.advanced;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.assertj.core.api.Assertions.assertThat;

class WireMockTest {

  @Test
  void testNetworkCall() throws IOException, InterruptedException {
    WireMockServer wireMockServer =
      new WireMockServer(WireMockConfiguration.wireMockConfig().port(8888));

    wireMockServer.start();

    WireMock.configureFor("localhost", wireMockServer.getOptions().portNumber());
    WireMock.stubFor(WireMock.get("/springio")
      .willReturn(aResponse()
        .withHeader("Content-Type", "application/json")
        .withBody("{\"message\": \"Hello, WireMock!\"}")));

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create("http://localhost:" + wireMockServer.getOptions().portNumber() + "/springio"))
      .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    System.out.println(response.body());

    assertThat(response.statusCode()).isEqualTo(200);
    assertThat(response.body()).isEqualTo("{\"message\": \"Hello, WireMock!\"}");

    wireMockServer.stop();
  }
}
