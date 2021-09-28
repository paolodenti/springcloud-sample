# Spring Cloud Microservices sample project

* Microservices architecture
* Eureka Discovery
* Cloud Config Server with Encryption
* JPA configuration from Config Server
* Eureka load balancing on backend service
* Resilience samples with Circuit Breaker, Retry, Rate Limiter
* Gateway Server with custom routing
* **Single code base just for sample purposes**
* **Java >= 11**

## Start locally via Docker Compose

* dev profile: `docker compose -f docker/docker-compose-dev.yml up`
* prod profile: `ENCRYPT_KEY=MY_SUPER_SECRET_PASSWORD docker compose -f docker/docker-compose-prod.yml up`

<!-- markdownlint-disable MD036 -->
**It might take 2/3 minutes to have the product service registered with load balancing on Eureka**
<!-- markdownlint-enable MD036 -->

## Info

* config server on port 8000
* gatewayserver on port 8500
* eureka server on port 9000
* dashboard on port 8080
* products on port 8081

## Entrypoints

* Eureka: [http://127.0.0.1:9000](http://127.0.0.1:9000)
* Config Server: on `http://127.0.0.1:8000` e.g. [http://127.0.0.1:8000/product/default](http://127.0.0.1:8000/product/default)
* Products h2 console: [http://127.0.0.1:8081/h2-console](http://127.0.0.1:8081/h2-console)
  * default profile: `jdbc:h2:mem:products`
  * dev profile: `jdbc:h2:mem:products-dev`
  * prod profile: `jdbc:h2:mem:products-prod`
* Products props from config server: [http://127.0.0.1:8081/test-property](http://127.0.0.1:8081/test-property)
* Dashboard props from config server: [http://127.0.0.1:8080/test-property](http://127.0.0.1:8080/test-property)
* Products: [http://127.0.0.1:8081/product](http://127.0.0.1:8081/product)
* Dashboard (invoking multiple services): [http://127.0.0.1:8080/dashboard](http://127.0.0.1:8080/dashboard)

## Resilience

`http://127.0.0.1:8080/dashboard` is without resilience

### Circuit Breaker

`http://127.0.0.1:8080/dashboard/circuitbreaker` has a circuit breaker with 3000ms threshold.

Set a simulated network delay on the products microservice using [http://127.0.0.1:8081/product/conditioner/{delay}](http://127.0.0.1:8081/product/conditioner/{delay}) to set a delay in ms, e.g. `http://127.0.0.1:8081/product/conditioner/4000` for 4 seconds delay, triggering a circuit breaker failure.

After setting the delay, invoke `http://127.0.0.1:8080/dashboard/circuitbreaker` and analyze behavior in `http://127.0.0.1:8080/actuator/circuitbreakerevents/productsCircuitbreaker`.

Use [http://127.0.0.1:8081/product/conditioner/0] to remove the simulated network delay(`http://127.0.0.1:8081/product/conditioner/0`) to remove the simulated network delay.

### Retry

`http://127.0.0.1:8080/dashboard/retry` has a retry with 3 max attempt.

Create a fake delay as for the circuit breaker.

After setting the delay, invoke `http://127.0.0.1:8080/dashboard/retry` and analyze behavior in `http://127.0.0.1:8080/actuator/retryevents/productsRetry`.

### Rate Limiter

`http://127.0.0.1:8080/dashboard/rateLimiter` has a rat eof 1 request every 5 seconds, with 5 seconds waiting time.

After setting the delay, invoke `http://127.0.0.1:8080/dashboard/retry` and analyze behavior in `http://127.0.0.1:8080/actuator/retryevents/productsRetry`.

## Gateway Server

Configured on port 8500

passthrough to eureka LB on

* `http://127.0.0.1:8500/dashboard/dashboard`
* `http://127.0.0.1:8500/products/product`

Passthrouh can be removed setting to false `spring.cloud.gateway.discovery.locator.enabled`

Rewrites on

* `http://127.0.0.1:8500/v1/dashboard/*` ==> `lb://DASHBOARD/*` with custom header `X-My-Custom-Header` added
* `http://127.0.0.1:8500/v1/products/` ==> `lb://PRODUCTS/*` with custom header `X-My-Custom-Header` added

## Correlation ID

Every request passing through the Gateway Server gets a correlation ID in then HTTP custom header (the header name `X-Correlation-ID` should be externalized).

The dashbosard and products services print the correlation id just for demonstration.

## Build everything manually for compose

```bash
( cd services/configserver ; mvn clean spring-boot:build-image )
( cd services/eurekaserver ; mvn clean spring-boot:build-image )
( cd services/gatewayserver ; mvn clean spring-boot:build-image )
( cd services/products ; mvn clean spring-boot:build-image )
( cd services/dashboard ; mvn clean spring-boot:build-image )
```
