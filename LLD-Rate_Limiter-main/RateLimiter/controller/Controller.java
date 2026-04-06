package RateLimiter.controller;

import RateLimiter.model.Request;
import RateLimiter.model.Response;
import RateLimiter.service.APIService;

public class Controller {
    private final APIService apiService;

    public Controller(APIService apiService) {
        this.apiService = apiService;
    }

    public Response handleRequest(Request request) {
        return apiService.handle(request);
    }
}
