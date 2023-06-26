package org.tnmk.tech_common.csv;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.tnmk.tech_common.StringUtils;
import org.tnmk.tech_common.reflection.ObjectConverter;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class BeanCsvMapper<T> implements CsvMapper<T> {

  private static final String DEFAULT_DELIMITER = ",";
  private final Class<T> beanClass;
  private final String delimiter;

  private final String headersLine;
  private final Map<Integer, PropertyDescriptor> mapColumnToBeanField = new HashMap<>();

  public BeanCsvMapper(Class<T> beanClass, String delimiter, String headersLine) {
    this.delimiter = delimiterOrDefault(delimiter);
    this.beanClass = beanClass;
    this.headersLine = headersLine;
    this.registerHeadersLine(this.headersLine, this.delimiter);
  }


  @SneakyThrows
  @Override
  public T parseLine(String line) {
    String[] values = StringUtils.splitByDelimiterOutsideQuote(line, this.delimiter);
    T bean = beanClass.getConstructor().newInstance();
    for (int i = 0; i < values.length; i++) {
      String value = values[i];
      PropertyDescriptor propertyDescriptor = this.mapColumnToBeanField.get(i);
      if (propertyDescriptor == null) {
        log.warn("Value in column [{}] will be ignored because cannot find any corresponding column in the headersLine\n'{}'" +
            "\nvalue:'{}', mapColumnToBeanField:\n{}"
          , i, this.headersLine, value, this.mapColumnToBeanField);
        continue;
      }
      Class<?> propertyType = propertyDescriptor.getPropertyType();
      Object valueInCorrectType = ObjectConverter.convert(value, propertyType);
      propertyDescriptor.getWriteMethod().invoke(bean, valueInCorrectType);
    }
    return bean;
  }


  private void registerHeadersLine(String headersLine, String delimiter) {
    String[] headers = StringUtils.splitByDelimiterOutsideQuote(headersLine, delimiter);
    for (int i = 0; i < headers.length; i++) {
      String header = StringUtils.toFieldName(headers[i].trim());
      PropertyDescriptor propertyDescriptor = BeanUtils.getPropertyDescriptor(beanClass, header);
      if (propertyDescriptor == null) {
        String message = String.format(
          "The header in csv is '%s', but there's no such field name in class %s" +
            ", hence this column will be ignored when registeringHeadersLine '%s'.",
          header, beanClass.getSimpleName(), headersLine
        );
        log.warn(message);
      }
      mapColumnToBeanField.put(i, propertyDescriptor);
    }
  }



  private String delimiterOrDefault(String delimiter) {
    return org.springframework.util.StringUtils.hasText(delimiter) ? delimiter : DEFAULT_DELIMITER;
  }
}
