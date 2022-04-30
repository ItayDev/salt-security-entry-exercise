package org.maybe.salt.assignment.utils;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionUtils {
    public static <T> Map<String, T> groupBy(Stream<T> collection, Function<T, String> keyExtractor) {
        return collection.collect(Collectors.toMap(keyExtractor, Function.identity()));
    }

    public static <k, v> boolean isMapEmpty(Map<k, v> map) {
        return map == null || map.isEmpty();
    }
}
