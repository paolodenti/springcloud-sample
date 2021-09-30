package com.github.paolodenti.dashboard.web;

import com.github.paolodenti.dashboard.service.client.PostsFeignClient;
import com.github.paolodenti.dashboard.service.client.ProductsFeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    final private Logger logger = LoggerFactory.getLogger(DashboardController.class);

    private final ProductsFeignClient productsFeignClient;

    private final PostsFeignClient postsFeignClient;

    public DashboardController(ProductsFeignClient productsFeignClient, PostsFeignClient postsFeignClient) {
        this.productsFeignClient = productsFeignClient;
        this.postsFeignClient = postsFeignClient;
    }

    // no resilience
    @GetMapping
    public Map<String, Object> noCheck(@RequestHeader("X-Correlation-ID") String correlationId) {
        logger.info("Received correlation ID: '{}'", correlationId);
        logger.info("executing dashboard noCheck");
        return Map.ofEntries(Map.entry("products", productsFeignClient.getProducts(correlationId)), Map.entry("posts", postsFeignClient.getPosts()));
    }

    // circuitbreaker resilience
    @GetMapping("/circuitbreaker")
    @CircuitBreaker(name = "productsCircuitbreaker", fallbackMethod = "circuitbreakerFallBack")
    public Map<String, Object> circuitbreaker(@RequestHeader("X-Correlation-ID") String correlationId) {
        logger.info("executing dashboard circuitbreaker");
        return Map.ofEntries(Map.entry("error", false), Map.entry("products", productsFeignClient.getProducts(correlationId)), Map.entry("posts", postsFeignClient.getPosts()));
    }

    private Map<String, Object> circuitbreakerFallBack(Throwable t) {
        return Map.of("error", true);
    }

    // retry resilience
    @GetMapping("/retry")
    @Retry(name = "productsRetry", fallbackMethod = "retryFallBack")
    public Map<String, Object> retry(@RequestHeader("X-Correlation-ID") String correlationId) {
        logger.info("executing dashboard retry");
        return Map.ofEntries(Map.entry("error", false), Map.entry("products", productsFeignClient.getProducts(correlationId)), Map.entry("posts", postsFeignClient.getPosts()));
    }

    private Map<String, Object> retryFallBack(Throwable t) {
        return Map.of("error", true);
    }

    // rate limiter
    @GetMapping("/rateLimiter")
    @RateLimiter(name = "productsRateLimiter", fallbackMethod = "rateLimiterFallBack")
    public Map<String, Object> rateLimiter(@RequestHeader("X-Correlation-ID") String correlationId) {
        logger.info("executing dashboard rateLimiter");
        return Map.ofEntries(Map.entry("error", false), Map.entry("products", productsFeignClient.getProducts(correlationId)), Map.entry("posts", postsFeignClient.getPosts()));
    }

    private Map<String, Object> rateLimiterFallBack(Throwable t) {
        return Map.of("error", true);
    }
}
