package db;

public interface Repository<K, V> {
    V get(K key);
    void put(K key, V value);
}
