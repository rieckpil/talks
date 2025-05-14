package de.rieckpil.talks.advanced;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
class TestcontainersIT {

  private static final int POSTGRES_PORT = 5432;

  @Container
  private static final GenericContainer<?> postgresManual = new GenericContainer<>(
    DockerImageName.parse("postgres:latest"))
    .withEnv("POSTGRES_USER", "testuser")
    .withEnv("POSTGRES_PASSWORD", "testpassword")
    .withEnv("POSTGRES_DB", "testdb")
    .withExposedPorts(POSTGRES_PORT)
    .waitingFor(Wait.forLogMessage(".*database system is ready to accept connections.*\\s", 1));

  @Container
  private static PostgreSQLContainer<?> postgresModule = new PostgreSQLContainer<>("postgres:latest")
    .withDatabaseName("testdb")
    .withUsername("testuser")
    .withPassword("testpass");

  @Test
  void testQuery() throws SQLException {

    // postgresContainer.start();

    String jdbcUrl = postgresModule.getJdbcUrl();
    String username = postgresModule.getUsername();
    String password = postgresModule.getPassword();
    Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

    System.out.println("We just started: " + connection.getMetaData().getDatabaseProductName());

    try (Statement statement = connection.createStatement()) {
      statement.execute("CREATE TABLE test (id SERIAL PRIMARY KEY, name VARCHAR(255))");
      statement.execute("INSERT INTO test (name) VALUES ('test-name')");
    }

    String query = "SELECT name FROM test WHERE id = 1";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        String result = resultSet.getString("name");
        assertThat(result).isEqualTo("test-name");
      }
    }

    postgresModule.stop();
  }
}
