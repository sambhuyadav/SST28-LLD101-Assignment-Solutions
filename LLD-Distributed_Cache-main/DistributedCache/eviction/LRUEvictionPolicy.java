package eviction;

import java.util.LinkedHashSet;

public class LRUEvictionPolicy<K> implements EvictionPolicy<K> {

    private final LinkedHashSet<K> accessOrder = new LinkedHashSet<>();

    @Override
    public void keyAccessed(K key) {
        accessOrder.remove(key);
        accessOrder.add(key);
    }

    @Override
    public K evictKey() {
        if (accessOrder.isEmpty()) {
            throw new IllegalStateException("No keys available for eviction");
        }
        K lruKey = accessOrder.iterator().next();
        accessOrder.remove(lruKey);
        return lruKey;
    }
}
