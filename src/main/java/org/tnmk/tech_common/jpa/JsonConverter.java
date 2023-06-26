package org.tnmk.tech_common.jpa;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import org.tnmk.tech_common.json.JsonHelper;
import org.tnmk.tech_common.json.ObjectMapperHelper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@Converter
@RequiredArgsConstructor
public abstract class JsonConverter<T> implements AttributeConverter<T, String> {

  private final JsonHelper jsonHelper = new JsonHelper(ObjectMapperHelper.objectMapper());
  private final Type sourceType;

  public JsonConverter() {
    this.sourceType = getParameterizedType();
  }

  @Override
  public String convertToDatabaseColumn(T sourceObject) {
    if (sourceObject == null) {
      return null;
    }
    return jsonHelper.toJson(sourceObject);
  }

  @Override
  public T convertToEntityAttribute(String jsonString) {
    if (jsonString == null) {
      return null;
    }
    return jsonHelper.toObject(jsonString, sourceType);
  }

  private Type getParameterizedType() {
    ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
    Type[] types = parameterizedType.getActualTypeArguments();
    return types[0];
  }
}