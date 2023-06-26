package org.tnmk.tech_common;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Slf4j
class NumberUtilsTest {
  @ParameterizedTest
  @ValueSource(strings = {"123", "123,456", "1,23", "123.45", "1,234.56", "1,23.45"})
  public void test_ToInteger(String numAsStr){
    Double value = NumberUtils.toNumber(numAsStr, Double.class);

    log.info("Converted value: "+value);
    Assertions.assertThat(value).isGreaterThan(0);
  }
}