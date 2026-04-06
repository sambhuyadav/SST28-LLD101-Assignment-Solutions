package cache;

import eviction.EvictionPolicy;

import java.util.HashMap;
import java.util.Map;

public class CacheNode<K, V> {

    private final Map<K, CacheEntry<V>> storage = new HashMap<>();
    private final EvictionPolicy<K> evictionPolicy;
    private final int capacity;
    private final String nodeId;

    public CacheNode(String nodeId, int capacity, EvictionPolicy<K> evictionPolicy) {
        this.nodeId = nodeId;
        this.capacity = capacity;
        this.evictionPolicy = evictionPolicy;
    }

    public V get(K key) {
        CacheEntry<V> entry = storage.get(key);

        if (entry == null) {
            System.out.println("[" + nodeId + "] MISS for key='" + key + "'");
            return null;
        }

        if (entry.isExpired()) {
            System.out.println("[" + nodeId + "] EXPIRED entry for key='" + key + "' — removing.");
            storage.remove(key);
            return null;
        }

        evictionPolicy.keyAccessed(key);
        System.out.println("[" + nodeId + "] HIT for key='" + key + "'");
        return entry.getValue();
    }

    public void put(K key, V value, long ttlMillis) {
        if (!storage.containsKey(key) && storage.size() >= capacity) {
            K evictedKey = evictionPolicy.evictKey();
            storage.remove(evictedKey);
            System.out.println("[" + nodeId + "] Evicted key='" + evictedKey
                    + "' to make room (policy: " + evictionPolicy.getClass().getSimpleName() + ")");
        }

        storage.put(key, new CacheEntry<>(value, ttlMillis));
        evictionPolicy.keyAccessed(key);
        System.out.println("[" + nodeId + "] Stored key='" + key + "'");
    }

    public String getNodeId() {
        return nodeId;
    }
}
