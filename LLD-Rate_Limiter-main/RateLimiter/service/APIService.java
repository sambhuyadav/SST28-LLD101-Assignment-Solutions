package RateLimiter.service;

import RateLimiter.model.Request;
import RateLimiter.model.Response;
import RateLimiter.proxy.IRemoteResource;

public class APIService {
    private final IRemoteResource resource; 

    public APIService(IRemoteResource resource) {
        this.resource = resource;
    }

    public Response handle(Request request) {
        System.out.println("Executing Business Logic for " + request.getApiName());
        
        boolean needsExternalResource = (boolean) request.getPayload().getOrDefault("needsExternal", true);

        if (needsExternalResource) {
            return resource.call(request);
        } else {
            return new Response(200, "Success without External Resource", "Local Data");
        }
    }
}
