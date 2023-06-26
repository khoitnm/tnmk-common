package org.tnmk.tech_common;

public class NumberUtils {
  public static <T extends Number> T toNumber(String numberText, Class<T> numberClass) {
    // Remove any commas from the input string
    String sanitizedInput = numberText.replace(",", "");
    return org.springframework.util.NumberUtils.parseNumber(sanitizedInput, numberClass);
  }
}
