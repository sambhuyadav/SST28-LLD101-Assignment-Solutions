package service;

import cache.CacheRouter;
import db.Repository;

public class BackendService<K, V> {

    private final CacheRouter<K, V> cacheRouter;
    private final Repository<K, V> repository;

    public BackendService(CacheRouter<K, V> cacheRouter, Repository<K, V> repository) {
        this.cacheRouter = cacheRouter;
        this.repository = repository;
    }

    public V get(K key) {
        System.out.println("\n[BackendService] GET key='" + key + "'");

        V value = cacheRouter.get(key);

        if (value != null) {
            return value;
        }

        value = repository.get(key);

        if (value != null) {
            System.out.println("[BackendService] Populating cache for key='" + key + "'");
            cacheRouter.put(key, value);
        }

        return value;
    }

    public void put(K key, V value) {
        System.out.println("\n[BackendService] PUT key='" + key + "' value='" + value + "'");
        cacheRouter.put(key, value);
        repository.put(key, value);
    }
}
