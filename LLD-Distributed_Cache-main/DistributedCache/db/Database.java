package db;

import java.util.HashMap;
import java.util.Map;

public class Database<K, V> implements Repository<K, V> {

    private final Map<K, V> store = new HashMap<>();

    @Override
    public V get(K key) {
        V value = store.get(key);
        if (value != null) {
            System.out.println("[DB] Cache miss — fetched key='" + key + "' from database.");
        } else {
            System.out.println("[DB] Key='" + key + "' not found in database.");
        }
        return value;
    }

    @Override
    public void put(K key, V value) {
        store.put(key, value);
        System.out.println("[DB] Stored key='" + key + "' in database.");
    }
}
