package org.tnmk.tech_common.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.stereotype.Component;

/**
 * This class provides convenient methods for supporting Json conversion.
 */
@Component
public class JsonHelper {
  /**
   * This ObjectMapper should be configured in SpringContext with an appropriated dateTime config.
   */
  private final ObjectMapper objectMapper;

  public JsonHelper(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public String toJson(Object object) {
    try {
      return objectMapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      throw new IllegalStateException(e);
    }
  }

  public <T> T toObject(String jsonString, Class<T> resultClass) {
    try {
      return objectMapper.readValue(jsonString, resultClass);
    } catch (JsonProcessingException e) {
      throw new IllegalStateException("Cannot parse json to " + resultClass, e);
    }
  }

  public <T> T toObject(String jsonString, java.lang.reflect.Type resultClass) {
    try {
      TypeFactory typeFactory = objectMapper.getTypeFactory();
      JavaType javaType = typeFactory.constructType(resultClass);
      return objectMapper.readValue(jsonString, javaType);
    } catch (JsonProcessingException e) {
      throw new IllegalStateException("Cannot parse json to %s. Json String:\n %s".formatted(resultClass, jsonString), e);
    }
  }
}
