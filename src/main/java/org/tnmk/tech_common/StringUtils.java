package org.tnmk.tech_common;

public class StringUtils {
  public static String[] splitByDelimiterOutsideQuote(String string, String delimiter) {
    // Guideline: https://stackoverflow.com/questions/1757065/java-splitting-a-comma-separated-string-but-ignoring-commas-in-quotes
    // For example: delimiter = ","
    // => delimiterRegExp = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
    // means: "split on the comma only if that comma has zero, or an even number of quotes ahead of it."
    String delimiterRegExp = delimiter + "(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
    return string.split(delimiterRegExp, -1);
  }

  /**
   * @param input
   * @return this's similar to CamelCase, but the first character is lower case.
   */
  public static String toFieldName(String input) {
    String camelCase = toCamelCase(input);
    if (isFirstTwoCharactersUppercase(camelCase)) {
      return camelCase;
    } else {
      return replaceFirstCharByLowercase(camelCase);
    }
  }
  public static String replaceFirstCharByLowercase(String str) {
    if (str == null || str.isEmpty()) {
      return str;
    }
    char firstChar = str.charAt(0);
    if (Character.isLowerCase(firstChar)) {
      return str;
    }
    char[] chars = str.toCharArray();
    chars[0] = Character.toLowerCase(firstChar);
    return new String(chars);
  }

  private static boolean isFirstTwoCharactersUppercase(String str) {
    if (str == null || str.length() < 2) {
      return false;
    }
    for (int i = 0; i < 2; i++) {
      char c = str.charAt(i);
      if (!Character.isUpperCase(c)) {
        return false;
      }
    }
    return true;
  }


  public static String toCamelCase(String str) {
    if (str == null || str.isEmpty()) {
      return str;
    }
    StringBuilder camelCase = new StringBuilder();
    boolean capitalizeNext = false;
    for (char c : str.toCharArray()) {
      if (c == ' ' || c == '-' || c == '_') {
        capitalizeNext = true;
      } else if (capitalizeNext) {
        camelCase.append(Character.toUpperCase(c));
        capitalizeNext = false;
      } else {
        camelCase.append(c);
      }
    }
    return camelCase.toString();
  }


//  public static String toCamelCase(String input) {
//    if (input == null || input.isEmpty()) {
//      return "";
//    }
//
//    StringBuilder camelCase = new StringBuilder();
//    String[] words = input.trim().split("\\s+");
//
////    camelCase.append(words[0].toUpperCase());
//    for (int i = 0; i < words.length; i++) {
//      String word = words[i];
//      camelCase.append(Character.toUpperCase(word.charAt(0)));
//      camelCase.append(word.substring(1).toLowerCase());
//    }
//
//    return camelCase.toString();
//  }
}
