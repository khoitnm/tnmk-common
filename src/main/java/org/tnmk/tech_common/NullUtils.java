package org.tnmk.tech_common;

import java.util.Arrays;
import java.util.List;

public interface NullUtils {
  static boolean onlyOneOfElementsIsNotNull(Object... objects) {
    List<Object> notNulls = Arrays.stream(objects).filter(o -> o != null).toList();
    return notNulls.size() == 1;
  }

  static boolean someOfElementsIsNotNull(Object... objects) {
    List<Object> notNulls = Arrays.stream(objects).filter(o -> o != null).toList();
    return notNulls.size() >= 1;
  }
}
