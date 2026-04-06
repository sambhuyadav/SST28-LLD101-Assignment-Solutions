package RateLimiter.proxy;

import RateLimiter.model.Request;
import RateLimiter.model.Response;

public interface IRemoteResource {
    Response call(Request request);
}
