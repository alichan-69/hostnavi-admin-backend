package com.alichan.hostnavi.admin.util;

import java.io.InputStream;
import com.alichan.hostnavi.admin.error.Assert;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
  public static String convertJsonToString(String path) {
    String jsonString = "";
    try (InputStream input =
        Thread.currentThread().getContextClassLoader().getResourceAsStream(path)) {
      ObjectMapper mapper = new ObjectMapper();
      JsonNode jsonNode = mapper.readValue(input, JsonNode.class);
      jsonString = mapper.writeValueAsString(jsonNode);

      return jsonString;
    } catch (Exception error) {
      Assert.serverFail("Jsonの読み込みに失敗しました。");
      return jsonString;
    }
  }
}
