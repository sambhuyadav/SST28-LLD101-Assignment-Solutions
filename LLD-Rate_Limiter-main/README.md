# Pluggable Rate Limiting System for External Resource Usage

A backend rate-limiting system that controls access to external, paid resources. This system strategically intercepts requests before external calls are made, reducing costs and optimizing quota usage.

## 📋 Requirements

### Functional Requirements
- Allow or deny external resource calls based on rate limits
- Multiple pluggable algorithms:
  - Fixed Window Counter
  - Sliding Window Counter
- Runtime configuration of rate limits (e.g., 100 requests/minute)
- Flexible rate-limiting keys (customer, tenant, API key, provider)
- Simple interface for integration with internal services

### Non-Functional Requirements
- **Extensible**: Add new algorithms (Token Bucket, Leaky Bucket, etc.) with ease
- **SOLID/OOP Principles**: Clean, maintainable architecture
- **Thread-Safe**: Handles concurrent requests safely
- **High Performance**: Minimal computational and memory overhead

## 📐 Design Patterns

The system combines two key design patterns:

**Proxy Pattern**: `RemoteResourceProxy` transparently wraps external resource calls and enforces rate-limiting checks before reaching the provider.

**Strategy Pattern**: Rate-limiting algorithms implement the `RateLimiterStrategy` interface, enabling seamless switching between `FixedWindowRateLimiter` and `SlidingWindowRateLimiter` without modifying business logic.

![UML Design](UML.png)

## 🚀 Getting Started

### Project Structure
```
Rate-Limiter-Design/
└── RateLimiter/
    ├── Main.java
    ├── config/
    ├── controller/
    ├── model/
    ├── proxy/
    ├── resolver/
    ├── service/
    └── strategy/
```

### Compilation
Run from the project root:
```bash
javac -d bin RateLimiter/Main.java RateLimiter/model/*.java RateLimiter/config/*.java RateLimiter/resolver/*.java RateLimiter/strategy/*.java RateLimiter/service/*.java RateLimiter/proxy/*.java RateLimiter/controller/*.java
```

### Execution
```bash
java -cp bin RateLimiter.Main
```