package RateLimiter.proxy;

import RateLimiter.model.Request;
import RateLimiter.model.Response;

public class ActualRemoteResource implements IRemoteResource {
    @Override
    public Response call(Request request) {
        System.out.println("Calling External Resource for: " + request.getClientId());
        return new Response(200, "Success from External Resource", "Extracted Data for " + request.getClientId());
    }
}
