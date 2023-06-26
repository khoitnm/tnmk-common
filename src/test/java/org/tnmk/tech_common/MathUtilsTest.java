package org.tnmk.tech_common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MathUtilsTest {

  @ParameterizedTest
  @CsvSource({
    // number ,expectedPowerOfTwo
    "0        ,false",
    "1        ,true",// 2 ^ 0 = 1
    "-1       ,false",
    "10       ,false",
    "2        ,true",
    "3        ,false",
    "16       ,true",
    "14       ,false",
    "-2       ,false",
    "-4       ,false",
    "-8       ,false",
  })
  void isPowerOfTwo(int number, boolean expectedPowerOfTwo) {
    boolean actualPowerOfTwo = MathUtils.isPowerOfTwo(number);

    Assertions.assertEquals(expectedPowerOfTwo, actualPowerOfTwo);
  }

  @Test
  void isPowerOfNumber() {
  }
}