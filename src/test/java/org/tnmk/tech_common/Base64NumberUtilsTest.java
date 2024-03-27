package org.tnmk.tech_common;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class Base64NumberUtilsTest {

    @ParameterizedTest
    @ValueSource(longs = {17115597240937792l, 1711559724093779200l})
    void toBase64(long decimalNumber) {
      String result = Base64NumberUtils.toBase64(decimalNumber);

      log.info(result);
    }
}
