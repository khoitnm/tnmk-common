package org.tnmk.tech_common.csv;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SimpleCsvReader {
  public <T> List<T> readCsvWithHeadersLineToBean(String content, String delimiter, Class<T> beanClass) {
    String[] lines = content.split("\n");
    List<T> models = new ArrayList<>(lines.length - 1);
    String headersLine = lines[0].trim();
    BeanCsvMapper<T> beanCsvMapper = new BeanCsvMapper<>(beanClass, delimiter, headersLine);
    for (int i = 1; i < lines.length; i++) {
      String line = lines[i].trim();
      if (line == null || line.isEmpty()) {
        continue;
      }
      if (line.startsWith("#") || line.startsWith("//")) {//ignore comment
        continue;
      }
      T model = beanCsvMapper.parseLine(line);
      models.add(model);
    }
    return models;
  }
}
