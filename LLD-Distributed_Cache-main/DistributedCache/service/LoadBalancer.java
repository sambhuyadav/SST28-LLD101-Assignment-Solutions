package service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class LoadBalancer<K, V> {

    private final List<BackendService<K, V>> services;
    private final AtomicInteger counter = new AtomicInteger(0);

    public LoadBalancer(List<BackendService<K, V>> services) {
        if (services == null || services.isEmpty()) {
            throw new IllegalArgumentException("At least one BackendService is required.");
        }
        this.services = new ArrayList<>(services);
    }

    public BackendService<K, V> getNextService() {
        int index = Math.floorMod(counter.getAndIncrement(), services.size());
        return services.get(index);
    }

    public V forwardGet(K key) {
        return getNextService().get(key);
    }

    public void forwardPut(K key, V value) {
        getNextService().put(key, value);
    }
}
