package org.tnmk.tech_common.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ObjectMapperHelper {

  /**
   * This object is used for JSON conversion which also support date types in Java 8+
   */
  public static ObjectMapper objectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    // This will help convert modern date types in Java 8 (ZonedDateTime, Instant, etc.)
    objectMapper.registerModule(new JavaTimeModule());
    return objectMapper;
  }
}
