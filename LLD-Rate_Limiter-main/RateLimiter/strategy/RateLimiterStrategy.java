package RateLimiter.strategy;

import RateLimiter.config.RateLimitConfig;

public interface RateLimiterStrategy {
    boolean allowRequest(String key, RateLimitConfig config);
}
