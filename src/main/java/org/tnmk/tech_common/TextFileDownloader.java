package org.tnmk.tech_common;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TextFileDownloader {
  private final RestTemplate restTemplate;

  public TextFileDownloader(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
  }

  public String downloadFile(String url){
    ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
    String content = responseEntity.getBody();
    return content;
  }
}
