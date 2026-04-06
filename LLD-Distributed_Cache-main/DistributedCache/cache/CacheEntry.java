package cache;

public class CacheEntry<V> {

    private final V value;
    private final long expiryTime;

    public CacheEntry(V value, long ttlMillis) {
        this.value = value;
        this.expiryTime = (ttlMillis == Long.MAX_VALUE)
                ? Long.MAX_VALUE
                : System.currentTimeMillis() + ttlMillis;
    }

    public V getValue() {
        return value;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > expiryTime;
    }
}
