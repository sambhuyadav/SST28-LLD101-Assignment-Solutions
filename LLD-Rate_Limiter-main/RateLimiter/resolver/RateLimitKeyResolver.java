package RateLimiter.resolver;

import RateLimiter.model.Request;

public interface RateLimitKeyResolver {
    String resolve(Request request);
}
