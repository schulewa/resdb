package com.apschulewitz.resdb.common.utils;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Created by adrianschulewitz on 20/08/2016.
 */
public class ImmutableCollectionUtils {


    public static <T> Collector<T, ImmutableList.Builder<T>, ImmutableList<T>> toImmutableList() {
        return Collector.of(
                ImmutableList.Builder::new,
                ImmutableList.Builder::add,
                (b1, b2) -> b1.addAll(b2.build()),
                (builder) -> builder.build()
        );
    }

    public static <T> Collector<T, ImmutableSet.Builder<T>, ImmutableSet<T>> toImmutableSet() {
        return Collector.of(
                ImmutableSet.Builder::new,
                ImmutableSet.Builder::add,
                (b1, b2) -> b1.addAll(b2.build()),
                (builder) -> builder.build()
        );
    }

    public static <T, K, V> Collector<T, ?, ImmutableMap<K, V>> toImmutableMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends V> valueMapper) {

        Supplier<ImmutableMap.Builder<K, V>> supplier = ImmutableMap.Builder::new;
        BiConsumer<ImmutableMap.Builder<K, V>, T> accumulator = (b, t) -> b.put(keyMapper.apply(t), valueMapper.apply(t));
        BinaryOperator<ImmutableMap.Builder<K, V>> combiner = (l, r) -> l.putAll(r.build());
        Function<ImmutableMap.Builder<K, V>, ImmutableMap<K, V>> finisher = ImmutableMap.Builder::build;

        return Collector.of(supplier, accumulator, combiner, finisher);
    }
}
