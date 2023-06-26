package org.tnmk.tech_common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapUtils {
    public static  <T> void addToListInMap(Map<String, List<T>> map, String key, T element) {
        List<T> list = map.get(key);
        synchronized (map) {
            if (list == null) {
                list = new ArrayList<>();
                map.put(key, list);
            }
        }

        list.add(element);
    }
}
