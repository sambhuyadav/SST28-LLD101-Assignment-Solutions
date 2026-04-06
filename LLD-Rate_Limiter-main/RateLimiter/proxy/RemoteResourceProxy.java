package RateLimiter.proxy;

import RateLimiter.model.Request;
import RateLimiter.model.Response;
import RateLimiter.service.RateLimiterService;

public class RemoteResourceProxy implements IRemoteResource {
    private final IRemoteResource realResource;
    private final RateLimiterService rateLimiterService;

    public RemoteResourceProxy(IRemoteResource realResource, RateLimiterService rateLimiterService) {
        this.realResource = realResource;
        this.rateLimiterService = rateLimiterService;
    }

    @Override
    public Response call(Request request) {
        if (rateLimiterService.isAllowed(request)) {
            return realResource.call(request);
        } else {
            System.err.println("Rate Limit Exceeded for key: " + request.getClientId());
            return new Response(429, "Too Many Requests - Quota Exceeded for External Resource", null);
        }
    }
}
