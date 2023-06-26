package org.tnmk.tech_common;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class ListUtils {
  private ListUtils() {
    // utils class
  }

  /**
   * @param list the list of items
   * @param top  the amount of items we want to get.
   *             If it's more than the total amount in the items, the method will return all items in the input list.
   * @param <T>
   * @return Get some first items from the list.
   */
  public static <T> List<T> getTop(List<T> list, int top) {
    List<T> result = new ArrayList<>(Math.min(top, list.size()));
    int i = 0;
    for (T item : list) {
      i++;
      if (i > top) {
        break;
      }
      result.add(item);
    }
    return result;
  }

  /**
   * @param list
   * @param startIndex inclusive index
   * @param endIndex   inclusive index
   * @param <T>
   * @return
   */
  public static <T> List<T> getSub(List<T> list, int startIndex, int endIndex) {
    List<T> result = new ArrayList<>(Math.min(endIndex - startIndex + 1, list.size()));
    int i = 0;
    for (T item : list) {
      i++;

      if (i < startIndex + 1) {
        continue;
      }
      if (i > endIndex + 1) {
        break;
      }
      result.add(item);
    }
    return result;
  }

  public static <T> List<T> getSub(List<T> list, int startIndex) {
    return getSub(list, startIndex, list.size() - 1);
  }

  /**
   * Note: this method mostly used for testing for now; but could potentially be used for main business logic, too.
   */
  public static List<Integer> splitToIntegers(String listString, String delimiter) {
    if (listString == null) {
      return null;
    }
    if (listString.trim().equals("")) {
      return Collections.emptyList();
    }
    String[] stringsArray = listString.split(delimiter);
    return Arrays.stream(stringsArray)
      .map(String::trim)
      .map(Integer::valueOf)
      .collect(Collectors.toList());
  }

  public static List<Integer> toIntegersList(List<String> strings) {
    return strings.stream().map(Integer::valueOf).collect(Collectors.toList());
  }

  public static List<String> toStringsList(List<?> items) {
    return items.stream().map(String::valueOf).collect(Collectors.toList());
  }

  public static <T> List<T> concat(List<T>... lists) {
    List<T> result = new ArrayList<>();
    for (List<T> list : lists) {
      result.addAll(list);
    }
    return result;
  }
}
