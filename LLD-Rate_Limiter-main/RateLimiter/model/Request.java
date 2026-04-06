package RateLimiter.model;

import java.util.Map;

public class Request {
    private final String clientId;
    private final String apiName;
    private final Map<String, Object> payload;

    public Request(String clientId, String apiName, Map<String, Object> payload) {
        this.clientId = clientId;
        this.apiName = apiName;
        this.payload = payload;
    }

    public String getClientId() {
        return clientId;
    }

    public String getApiName() {
        return apiName;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }
}
