package RateLimiter.strategy;

import RateLimiter.config.RateLimitConfig;
import java.util.Deque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class SlidingWindowRateLimiter implements RateLimiterStrategy {
    private final ConcurrentHashMap<String, Deque<Long>> requestLogs = new ConcurrentHashMap<>();

    @Override
    public synchronized boolean allowRequest(String key, RateLimitConfig config) {
        long now = System.currentTimeMillis();
        long windowStart = now - config.getWindowSizeInMillis();

        Deque<Long> timestamps = requestLogs.computeIfAbsent(key, k -> new ConcurrentLinkedDeque<>());

        while (!timestamps.isEmpty() && timestamps.peekFirst() <= windowStart) {
            timestamps.pollFirst();
        }

        if (timestamps.size() < config.getLimit()) {
            timestamps.addLast(now);
            return true;
        }

        return false;
    }
}
