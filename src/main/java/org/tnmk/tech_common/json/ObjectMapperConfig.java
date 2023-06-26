package org.tnmk.tech_common.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

  /**
   * This bean is helpful for JSON conversion.
   * This configuration is not required in a web app because it's already supported by spring-boot-starter-web.
   * However, this is a stand-alone app, so we have to initiate this bean.
   */
  @Bean
  public ObjectMapper objectMapper() {
    return ObjectMapperHelper.objectMapper();
  }
}
