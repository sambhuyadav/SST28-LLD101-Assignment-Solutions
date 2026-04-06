package RateLimiter.config;

public class RateLimitConfig {
    private final int limit;
    private final long windowSizeInMillis;

    public RateLimitConfig(int limit, long windowSizeInMillis) {
        this.limit = limit;
        this.windowSizeInMillis = windowSizeInMillis;
    }

    public int getLimit() {
        return limit;
    }

    public long getWindowSizeInMillis() {
        return windowSizeInMillis;
    }
}
