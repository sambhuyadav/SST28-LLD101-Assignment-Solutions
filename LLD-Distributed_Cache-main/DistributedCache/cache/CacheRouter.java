package cache;

import distribution.DistributionStrategy;

import java.util.List;

public class CacheRouter<K, V> {

    private final List<CacheNode<K, V>> nodes;
    private final DistributionStrategy<K> strategy;
    private static final long DEFAULT_TTL_MS = Long.MAX_VALUE;

    public CacheRouter(List<CacheNode<K, V>> nodes, DistributionStrategy<K> strategy) {
        this.nodes = nodes;
        this.strategy = strategy;
    }

    public V get(K key) {
        return resolveNode(key).get(key);
    }

    public void put(K key, V value) {
        put(key, value, DEFAULT_TTL_MS);
    }

    public void put(K key, V value, long ttlMillis) {
        resolveNode(key).put(key, value, ttlMillis);
    }

    private CacheNode<K, V> resolveNode(K key) {
        int index = strategy.getNodeIndex(key, nodes.size());
        CacheNode<K, V> node = nodes.get(index);
        System.out.println("[CacheRouter] key='" + key + "' → " + node.getNodeId()
                + " (index=" + index + ", strategy=" + strategy.getClass().getSimpleName() + ")");
        return node;
    }
}
