package org.tnmk.tech_common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

class ListUtilsTest {

  @Test
  void getSub() {
    int itemsCount = 5;
    List<Integer> list = IntStream.range(0, itemsCount)
        .boxed().toList();

    List<Integer> result = ListUtils.getSub(list, 1);
    Assertions.assertTrue(result.containsAll(List.of(1, 2, 3, 4)));
  }


}