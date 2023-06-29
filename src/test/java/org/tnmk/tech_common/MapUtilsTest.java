package org.tnmk.tech_common;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MapUtilsTest {

    @Test
    void addToListInMap() {
        // GIVEN
        Map<Integer, List<String>> map = new HashMap<>();

        // WHEN & THEN 1:
        MapUtils.addToListInMap(map, 1, null);
        Assertions.assertThat(map.get(1).get(0)).isNull();
        Assertions.assertThat(map.get(2)).isNull();

        // WHEN & THEN 2:
        MapUtils.addToListInMap(map, 1, "A");
        Assertions.assertThat(map.get(1).get(0)).isNull();
        Assertions.assertThat(map.get(1).get(1)).isEqualTo("A");
        Assertions.assertThat(map.get(2)).isNull();

        // WHEN & THEN 3:
        MapUtils.addToListInMap(map, 2, "2.1");
        Assertions.assertThat(map.get(2).get(0)).isEqualTo("2.1");
    }
}
