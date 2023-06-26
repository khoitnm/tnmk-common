package org.tnmk.tech_common;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

  @ParameterizedTest
  @CsvSource({
    // input  ,expectResult
    "abc      ,abc",
    "aBC      ,aBC",
    "first name ,firstName",
    "First name ,firstName",
    "First Name ,firstName",
    "first_name ,firstName",
    "First_name ,firstName",
    "firstName ,firstName",
  })
  void test_toFieldName(String input, String expectResult) {
    String result = StringUtils.toFieldName(input);

    Assertions.assertThat(result).isEqualTo(expectResult);
  }

  @ParameterizedTest
  @CsvSource({
    // input  ,expectResult
    "abc      ,abc",
    "aBC      ,aBC",
    "first name ,firstName",
    "First name ,FirstName",
    "First Name ,FirstName",
    "first_name ,firstName",
    "First_name ,FirstName",
    "firstName ,firstName",
  })
  void test_toCamelCase(String input, String expectResult) {
    String result = StringUtils.toCamelCase(input);

    Assertions.assertThat(result).isEqualTo(expectResult);
  }
}