package com.example.map;

import java.util.HashMap;
import java.util.Map;

/**
 * Flyweight Factory — creates and CACHES MarkerStyle objects.
 *
 * Core responsibility:
 *   Ensure that for any unique combination of (color, iconPath, size),
 *   only ONE MarkerStyle object ever exists in memory.
 *
 * How the cache key works:
 *   We concatenate the three intrinsic properties with a delimiter to form
 *   a unique String key, e.g. "red|/icons/restaurant.png|24".
 *   Two requests with identical properties will produce the same key and
 *   therefore receive the SAME object reference from the map.
 *
 * Why a static factory + static map?
 *   The cache must survive across all callers for the lifetime of the app.
 *   Making it static means every class shares the same pool without needing
 *   to pass the factory around (acceptable for a simple demo; in production
 *   you might inject it via a constructor or DI framework).
 */
public class MarkerStyleFactory {

    // The flyweight pool: key → shared MarkerStyle instance
    private static final Map<String, MarkerStyle> cache = new HashMap<>();

    /**
     * Returns a shared MarkerStyle for the given intrinsic properties.
     *
     * @param color    marker fill colour (e.g. "red")
     * @param iconPath path to the marker icon (e.g. "/icons/food.png")
     * @param size     pixel size of the marker icon
     * @return a cached (possibly brand-new) MarkerStyle instance
     */
    public static MarkerStyle getStyle(String color, String iconPath, int size) {
        // Build a deterministic, collision-resistant cache key
        String key = color + "|" + iconPath + "|" + size;

        /*
         * computeIfAbsent is ideal here:
         *   - If the key exists  → returns the cached object instantly (no allocation)
         *   - If the key is new  → calls the lambda, stores the result, returns it
         * Either way, exactly one object exists per unique key.
         */
        return cache.computeIfAbsent(key, k -> {
            System.out.println("[Factory] Creating NEW MarkerStyle for key: " + key);
            return new MarkerStyle(color, iconPath, size);
        });
    }

    /** Utility method — useful for QuickCheck verification. */
    public static int getCacheSize() {
        return cache.size();
    }
}
