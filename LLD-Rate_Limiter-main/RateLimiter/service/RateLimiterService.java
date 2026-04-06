package RateLimiter.service;

import RateLimiter.config.RateLimitConfig;
import RateLimiter.model.Request;
import RateLimiter.resolver.RateLimitKeyResolver;
import RateLimiter.strategy.RateLimiterStrategy;

public class RateLimiterService {
    private RateLimiterStrategy strategy;
    private RateLimitKeyResolver keyResolver;
    private RateLimitConfig config;

    public RateLimiterService(RateLimiterStrategy strategy,
                             RateLimitKeyResolver keyResolver,
                             RateLimitConfig config) {
        this.strategy = strategy;
        this.keyResolver = keyResolver;
        this.config = config;
    }

    public void setStrategy(RateLimiterStrategy strategy) {
        this.strategy = strategy;
    }

    public void setKeyResolver(RateLimitKeyResolver keyResolver) {
        this.keyResolver = keyResolver;
    }

    public void setConfig(RateLimitConfig config) {
        this.config = config;
    }

    public boolean isAllowed(Request request) {
        String key = keyResolver.resolve(request);
        return strategy.allowRequest(key, config);
    }
}
