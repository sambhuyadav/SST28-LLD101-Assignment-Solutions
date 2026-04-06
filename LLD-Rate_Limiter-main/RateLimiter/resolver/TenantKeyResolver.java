package RateLimiter.resolver;

import RateLimiter.model.Request;

public class TenantKeyResolver implements RateLimitKeyResolver {
    @Override
    public String resolve(Request request) {
        Object tenantId = request.getPayload().get("tenantId");
        return "tenant:" + (tenantId != null ? tenantId.toString() : "default");
    }
}
