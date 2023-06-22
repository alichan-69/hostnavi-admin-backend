package com.alichan.hostnavi.admin;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.csv.CsvDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Util {
  @Value("${testdb.url}")
  private String DATABASE_URL;
  @Value("${testdb.user}")
  private String DATABASE_USER;
  @Value("${testdb.password}")
  private String DATABASE_PASSWORD;

  public String convertJsonFileToString(String path) {
    String jsonString = "";
    try (InputStream input =
        Thread.currentThread().getContextClassLoader().getResourceAsStream(path)) {
      ObjectMapper mapper = new ObjectMapper();
      JsonNode jsonNode = mapper.readValue(input, JsonNode.class);
      jsonString = mapper.writeValueAsString(jsonNode);

      return jsonString;
    } catch (Exception error) {
      return jsonString;
    }
  }

  public void setUpDatabase(String path) {
    try (
        Connection connection =
            DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        Statement statement = connection.createStatement();) {

      statement.execute("call truncateAllTable;");

      IDatabaseConnection databaseConnection = new DatabaseConnection(connection);

      IDataSet dataset = new CsvDataSet(new File(path));

      DatabaseOperation.INSERT.execute(databaseConnection, dataset);
    } catch (Exception error) {
      System.out.println(error.getMessage());
      throw new Error();
    }
  }

}
