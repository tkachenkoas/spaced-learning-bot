package com.atstudio.spacedlearningbot.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BotCollectionUtils {

    public static <T, S> List<T> convertList(Iterable<S> source, Function<S, T> mapper) {
        return StreamSupport.stream(source.spliterator(), false)
                .map(mapper).collect(toList());
    }

    public static <T, K> Map<K, T> convertToMap(Iterable<T> source, Function<T, K> toKeyMapper) {
        return StreamSupport.stream(source.spliterator(), false)
                .collect(Collectors.toMap(toKeyMapper, Function.identity()));
    }

}
