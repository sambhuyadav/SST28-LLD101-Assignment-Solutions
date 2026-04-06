package RateLimiter;

import RateLimiter.config.RateLimitConfig;
import RateLimiter.controller.Controller;
import RateLimiter.model.Request;
import RateLimiter.model.Response;
import RateLimiter.proxy.ActualRemoteResource;
import RateLimiter.proxy.IRemoteResource;
import RateLimiter.proxy.RemoteResourceProxy;
import RateLimiter.resolver.ClientApiKeyResolver;
import RateLimiter.resolver.RateLimitKeyResolver;
import RateLimiter.service.APIService;
import RateLimiter.service.RateLimiterService;
import RateLimiter.strategy.FixedWindowRateLimiter;
import RateLimiter.strategy.RateLimiterStrategy;
import RateLimiter.strategy.SlidingWindowRateLimiter;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        RateLimitConfig config = new RateLimitConfig(3, 10000);

        RateLimitKeyResolver keyResolver = new ClientApiKeyResolver();

        RateLimiterStrategy fixedWindowStrategy = new FixedWindowRateLimiter();
        RateLimiterService rateLimiterService = new RateLimiterService(fixedWindowStrategy, keyResolver, config);

        IRemoteResource actualResource = new ActualRemoteResource();
        IRemoteResource proxyResource = new RemoteResourceProxy(actualResource, rateLimiterService);
        APIService apiService = new APIService(proxyResource);
        Controller controller = new Controller(apiService);

        System.out.println("=== Testing Fixed Window Counter (Limit 3/10s) ===");
        runTest(controller, "User1", true);
        runTest(controller, "User1", true);
        runTest(controller, "User1", false);
        runTest(controller, "User2", true);
        runTest(controller, "User1", true);
        runTest(controller, "User1", true);

        System.out.println("\n=== Switching Strategy to Sliding Window Counter ===");
        RateLimiterStrategy slidingWindowStrategy = new SlidingWindowRateLimiter();
        rateLimiterService.setStrategy(slidingWindowStrategy);

        runTest(controller, "User3", true); 
        runTest(controller, "User3", true); 
        runTest(controller, "User3", true); 
        runTest(controller, "User3", true); 

        System.out.println("\n=== End of Demo ===");
    }

    private static void runTest(Controller controller, String clientId, boolean needsExternal) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("needsExternal", needsExternal);
        Request request = new Request(clientId, "ExternalResourceAPI", payload);
        Response response = controller.handleRequest(request);
        System.out.println("[Client: " + clientId + "][NeedsExt: " + needsExternal + "][Response: " + response.getStatusCode() + " - " + response.getMessage() + "]");
    }
}
