package de.rieckpil.workshop.advanced;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.assertj.core.api.Assertions.assertThat;

class TestcontainersTest {

  private static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:latest")
    .withDatabaseName("testdb")
    .withUsername("testuser")
    .withPassword("testpass");

  @Test
  public void testQuery() throws SQLException {

    postgresContainer.start();

    String jdbcUrl = postgresContainer.getJdbcUrl();
    String username = postgresContainer.getUsername();
    String password = postgresContainer.getPassword();
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

    postgresContainer.stop();
  }
}
