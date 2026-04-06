package RateLimiter.resolver;

import RateLimiter.model.Request;

public class ClientApiKeyResolver implements RateLimitKeyResolver {
    @Override
    public String resolve(Request request) {
        return "api-key:" + request.getClientId();
    }
}
