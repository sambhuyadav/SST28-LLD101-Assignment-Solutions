package RateLimiter.strategy;

import RateLimiter.config.RateLimitConfig;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowRateLimiter implements RateLimiterStrategy {
    private final ConcurrentHashMap<String, AtomicInteger> windowCounts = new ConcurrentHashMap<>();

    @Override
    public boolean allowRequest(String key, RateLimitConfig config) {
        long currentWindow = System.currentTimeMillis() / config.getWindowSizeInMillis();
        String windowKey = key + ":" + currentWindow;

        AtomicInteger count = windowCounts.computeIfAbsent(windowKey, k -> new AtomicInteger(0));
        
        if (count.get() < config.getLimit()) {
            if (count.incrementAndGet() <= config.getLimit()) {
                return true;
            }
        }
        return false;
    }
}
