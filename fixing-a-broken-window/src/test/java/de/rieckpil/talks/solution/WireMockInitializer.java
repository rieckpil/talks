package de.rieckpil.talks.solution;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Order(Ordered.LOWEST_PRECEDENCE - 1000)
public class WireMockInitializer
        implements ApplicationContextInitializer<ConfigurableApplicationContext> {

  @Override
  public void initialize(ConfigurableApplicationContext applicationContext) {

    WireMockServer wireMockServer = new WireMockServer(new WireMockConfiguration().dynamicPort());
    wireMockServer.start();

    RSAKeyGenerator rsaKeyGenerator = new RSAKeyGenerator();
    rsaKeyGenerator.initializeKeys();

    OAuth2Stubs oAuth2Stubs = new OAuth2Stubs(wireMockServer, rsaKeyGenerator);
    oAuth2Stubs.stubForConfiguration();
    oAuth2Stubs.stubForJWKS();

    applicationContext.getBeanFactory().registerSingleton("oAuth2Stubs", oAuth2Stubs);
    applicationContext.getBeanFactory().registerSingleton("rsaKeyGenerator", rsaKeyGenerator);

    TestPropertyValues
            .of(
                    "spring.security.oauth2.resourceserver.jwt.issuer-uri",
                    "http://localhost:" + wireMockServer.port() + "/auth/realms/spring")
            .applyTo(applicationContext);
  }
}

