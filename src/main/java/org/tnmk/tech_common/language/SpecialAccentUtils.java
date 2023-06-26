package org.tnmk.tech_common.language;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Please view all special accent characters in
 * https://en.wikipedia.org/wiki/Wikipedia:Language_recognition_chart
 */
public class SpecialAccentUtils {

  //  public static String replaceSpecialAccentChars(String str) {
//    if (str == null || str.isEmpty()) {
//      return str;
//    }
//    StringBuilder sb = new StringBuilder();
//    String normalizedStr = Normalizer.normalize(str, Normalizer.Form.NFD);
//    for (int i = 0; i < normalizedStr.length(); i++) {
//      char ch = normalizedStr.charAt(i);
//      if (ch >= '\u0300' && ch <= '\u036f') {
//        // Replace special accent character with corresponding English character
//        ch = getEnglishCharForAccent(ch);
//      }
//      sb.append(ch);
//    }
//    return sb.toString();
//  }
//
//  private static char getEnglishCharForAccent(char accentChar) {
//    switch (accentChar) {
//      case 'À':
//      case 'Á':
//      case 'Â':
//      case 'Ã':
//      case 'Ä':
//      case 'Å':
//        return 'A';
//      case 'à':
//      case 'á':
//      case 'â':
//      case 'ã':
//      case 'ä':
//      case 'å':
//        return 'a';
//      case 'È':
//      case 'É':
//      case 'Ê':
//      case 'Ë':
//        return 'E';
//      case 'è':
//      case 'é':
//      case 'ê':
//      case 'ë':
//        return 'e';
//      case 'Ì':
//      case 'Í':
//      case 'Î':
//      case 'Ï':
//        return 'I';
//      case 'ì':
//      case 'í':
//      case 'î':
//      case 'ï':
//        return 'i';
//      case 'Ò':
//      case 'Ó':
//      case 'Ô':
//      case 'Õ':
//      case 'Ö':
//        return 'O';
//      case 'ò':
//      case 'ó':
//      case 'ô':
//      case 'õ':
//      case 'ö':
//        return 'o';
//      case 'Ù':
//      case 'Ú':
//      case 'Û':
//      case 'Ü':
//        return 'U';
//      case 'ù':
//      case 'ú':
//      case 'û':
//      case 'ü':
//        return 'u';
//      case 'Ç':
//        return 'C';
//      case 'ç':
//        return 'c';
//      case 'ñ':
//        return 'n';
//      case 'Ñ':
//        return 'N';
//      case 'ß':
//        return 's';
//      case '¿':
//      case '¡':
//        return '\u0000';//empty character
//      default:
//        return accentChar;
//    }
//  }
  public static String replaceSpecialAccentChars(String name) {
    if (name == null) {
      return null;
    }
    if (name.isBlank()) {
      return name;
    }
    StringBuilder newName = new StringBuilder(name);

    for (Map.Entry<Pattern, String> entry : TWO_CHARS_REPLACEMENTS.entrySet()) {
      Matcher matcher = entry.getKey().matcher(newName);
      newName = new StringBuilder(matcher.replaceAll(entry.getValue()));
    }

    for (Map.Entry<Pattern, String> entry : ONE_CHAR_REPLACEMENTS.entrySet()) {
      Matcher matcher = entry.getKey().matcher(newName);
      newName = new StringBuilder(matcher.replaceAll(entry.getValue()));
    }

    return newName.toString();
  }

  private static final Map<Pattern, String> ONE_CHAR_REPLACEMENTS = oneCharReplacement();
  private static final Map<Pattern, String> TWO_CHARS_REPLACEMENTS = twoCharsReplacement();
  private static Map<Pattern, String> oneCharReplacement() {

    Map<Pattern, String> replacements = new HashMap<>();
    replacements.put(Pattern.compile("[åãáäâàāæą]"), "a");
    replacements.put(Pattern.compile("[ÅÃÁÄÂÀĀÆĄ]"), "A");

    replacements.put(Pattern.compile("[çćčĉ]"), "c");
    replacements.put(Pattern.compile("[ÇĆČĈ]"), "C");
    replacements.put(Pattern.compile("ð"), "D");
    replacements.put(Pattern.compile("Ð"), "D");

    replacements.put(Pattern.compile("[éëêèę]"), "e");
    replacements.put(Pattern.compile("[ÉËÊÈĘ]"), "E");
    replacements.put(Pattern.compile("ĥ"), "h");
    replacements.put(Pattern.compile("Ĥ"), "H");

    replacements.put(Pattern.compile("[ìíîïį]"), "i");
    replacements.put(Pattern.compile("[ÌÍÎÏĮ]"), "I");
    replacements.put(Pattern.compile("ĵ"), "j");
    replacements.put(Pattern.compile("Ĵ"), "J");
    replacements.put(Pattern.compile("[ĝğ]"), "g");
    replacements.put(Pattern.compile("[ĜĞ]"), "G");
    replacements.put(Pattern.compile("[ñňń]"), "n");
    replacements.put(Pattern.compile("[ÑŇŃ]"), "N");
    replacements.put(Pattern.compile("[õóöôòøǫ]"), "o");
    replacements.put(Pattern.compile("[ÕÓÖÔÒØǪ]"), "O");
    replacements.put(Pattern.compile("[úüûùų]"), "u");
    replacements.put(Pattern.compile("[ÚÜÛÙŲ]"), "U");
    replacements.put(Pattern.compile("ț"), "t");
    replacements.put(Pattern.compile("Ț"), "T");

    replacements.put(Pattern.compile("[şšș]"), "s");
    replacements.put(Pattern.compile("[ŞŠȘ]"), "S");
    replacements.put(Pattern.compile("[žźż]"), "z");
    replacements.put(Pattern.compile("[ŽŹŻ]"), "Z");

    replacements.put(Pattern.compile("¿"), "");

    // don't see this character in any language??? It was provided by ChatGPT
//      replacements.put(Pattern.compile("¡"), "");// Note: this is not "i" character!

    // Germany
    replacements.put(Pattern.compile("ẞ"), "SS"); // capitalized
    replacements.put(Pattern.compile("ß"), "ss");
    // Add other patterns here...
    return replacements;
  }
  private static Map<Pattern, String> twoCharsReplacement() {

    Map<Pattern, String> replacements = new HashMap<>();
    //ą́ is actually 2 chars, not one char, similarly for other chars in this function.
    replacements.put(Pattern.compile("ą́"), "a");
    replacements.put(Pattern.compile("Ą́"), "A");
    replacements.put(Pattern.compile("ę́"), "e");
    replacements.put(Pattern.compile("Ę́"), "E");
    replacements.put(Pattern.compile("į́"), "i");
    replacements.put(Pattern.compile("Į́"), "I");
    replacements.put(Pattern.compile("ǫ́"), "o");
    replacements.put(Pattern.compile("Ǫ́"), "O");
    replacements.put(Pattern.compile("ų́"), "u");
    replacements.put(Pattern.compile("Ų́"), "u");
    return replacements;
  }

  public static boolean containsSpecialAccentChars(String str) {
    // Implementation explanation:
    // The function first normalizes the input string using the Normalizer.normalize() method with the NFD normalization form.
    // This decomposes any composite characters into their component characters,
    // such as accents and diacritics.
    //
    // The function then iterates over each character in the normalized string and checks whether the character is a special accent character.
    // Special accent characters fall in the Unicode range U+0300 to U+036F.
    if (str == null || str.isEmpty()) {
      return false;
    }
    String normalizedStr = Normalizer.normalize(str, Normalizer.Form.NFD);
    for (int i = 0; i < normalizedStr.length(); i++) {
      char ch = normalizedStr.charAt(i);
      if (ch >= '\u0300' && ch <= '\u036f') {
        return true;
      }
    }
    return false;
  }
}
