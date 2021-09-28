package com.github.paolodenti.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class GatewayserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayserverApplication.class, args);
    }

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        // @formatter:off
        return builder.routes()
                .route(p -> p.path("/v1/dashboard/**")
                        .filters(f -> f.rewritePath("/v1/dashboard/(?<segment>.*)", "/${segment}")
                            .addResponseHeader("X-My-Custom-Header", String.valueOf(System.currentTimeMillis())))
                        .uri("lb://DASHBOARD"))
                .route(p -> p.path("/v1/products/**")
                        .filters(f -> f.rewritePath("/v1/products/(?<segment>.*)", "/${segment}")
                            .addResponseHeader("X-My-Custom-Header", String.valueOf(System.currentTimeMillis())))
                        .uri("lb://PRODUCTS"))
                .build();
        // @formatter:on
    }
}
