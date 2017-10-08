package com.github.vedenin.thirdpartylib.collections;

import com.github.vedenin.atom.annotations.*;
import com.github.vedenin.thirdpartylib.collections.exceptions.CollectionAtomException;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This Atom pattern (pattern that extends a Proxy/Facade pattern that have only minimal methods from original class)
 * This using to light-reference to another third party open-source libraries
 *
 * Created by Slava Vedenin on 12/16/2016.
 */
@Atom({HashMap.class, Map.class, LinkedHashMap.class, TreeMap.class})
@Isotopes({HashMap.class, LinkedHashMap.class, TreeMap.class})
@Molecule({SetAtom.class, ListAtom.class})
@AtomException(CollectionAtomException.class)
public class MapAtom<K, V> implements Iterable<MapAtom.Entry<K,V>> {
    private final Map<K, V> map;

    public SetAtom<K> keySet() {
        return SetAtom.getAtom(map.keySet());
    }

    public V get(K key) {
        return map.get(key);
    }

    public void set(K key, V value) {
        map.put(key, value);
    }

    public void put(K key, V value) {
        map.put(key, value);
    }

    public int size() {
        return map.size();
    }

    public ListAtom<V> values() {
        return ListAtom.create(map.values());
    }

    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    public void putAll(MapAtom<K, V> mapAtom) {
        map.putAll(mapAtom.getOriginal());
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    private static <T> BinaryOperator<T> throwingMerger() {
        return (u,v) -> { throw new CollectionAtomException(String.format("Duplicate key %s", u)); };
    }

    public void forEach(BiConsumer<? super K, ? super V> action) {
        map.forEach(action);
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<Entry<K, V>>() {
            private Iterator<Map.Entry<K, V>> iterator = map.entrySet().iterator();
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Entry<K, V> next() {
                return new Entry<>(iterator.next());
            }
        };
    }

    public static class Entry<K,V> implements Map.Entry<K,V> {
        private final K key;
        private V value;

        public Entry(Map.Entry<K, V> entry) {
            this.key = entry.getKey();
            this.value = entry.getValue();
        }

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            this.value = value;
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Entry<?, ?> entry = (Entry<?, ?>) o;

            if (key != null ? !key.equals(entry.key) : entry.key != null) return false;
            return value != null ? value.equals(entry.value) : entry.value == null;

        }

        @Override
        public int hashCode() {
            int result = key != null ? key.hashCode() : 0;
            result = 31 * result + (value != null ? value.hashCode() : 0);
            return result;
        }

        public static <K,V> Entry<K,V> create(K key, V value) {
            return new Entry<>(key, value);
        }
    }

    public static <T, K, U> Collector<T, ?, MapAtom<K,U>> getCollector(Function<? super T, ? extends K> keyMapper,
                                    Function<? super T, ? extends U> valueMapper) {
        return Collector.of(MapAtom::create,
                            (map, element) -> map.put(keyMapper.apply(element), valueMapper.apply(element)),
                            (left, right) -> { left.putAll(right); return left; });

    }

    public Stream<Entry<K, V>> stream() {
        return map.entrySet().stream().map(Entry::new);
    }

    // Just boilerplate code for Atom
    @BoilerPlate
    public static <K, V> MapAtom<K, V> getAtom(Map<K, V> map) {
        return new MapAtom<>(map);
    }

    @BoilerPlate
    private MapAtom() {
        this.map = new HashMap<>();
    }

    @BoilerPlate
    private MapAtom(int size) {
        this.map = new HashMap<>(size);
    }

    @BoilerPlate
    private MapAtom(Map<K, V> map) {
        this.map = map;
    }

    @BoilerPlate
    public static <K, V> MapAtom<K, V> create() {
        return new MapAtom<>();
    }

    @BoilerPlate
    public static <K, V> MapAtom<K, V> createTreeMap() {
        return new MapAtom<>(new TreeMap<>());
    }

    @BoilerPlate
    public static <K, V> MapAtom<K, V> createLinkedMap() {
        return new MapAtom<>(new LinkedHashMap<>());
    }

    @BoilerPlate
    public static <K, V> MapAtom<K, V> create(int size) {
        return new MapAtom<>(size);
    }

    @BoilerPlate
    public static <K, V> MapAtom<K, V> createLinkedMap(int size) {
        return new MapAtom<>(new LinkedHashMap<>(size));
    }

    @BoilerPlate
    private Map<K,V> getOriginal() {
        return map;
    }

    @BoilerPlate
    @Override
    public String toString() {
        return this.stream().map((s) -> s.getKey() + " = " + s.getValue()).
                collect(Collectors.joining(","));
    }
}
