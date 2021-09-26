# Spring Cloud Microservices sample project

* Microservices architecture
* Eureka Discovery
* Cloud Config Server with Encryption
* JPA configuration from Config Server
* Eureka load balancing on backend service
* **Single code base just for sample purposes**

## Start locally via Docker Compose

* dev profile: `docker compose -f docker/docker-compose-dev.yml up`
* prod profile: `ENCRYPT_KEY=MY_SUPER_SECRET_PASSWORD docker compose -f docker/docker-compose-prod.yml up`

<!-- markdownlint-disable MD036 -->
**It might take 2 minutes to have the product service registered with load balncing on Eureka**
<!-- markdownlint-enable MD036 -->

## Info

* config server on port 8090
* eureka server on port 8070
* rest-api on port 8080
* products on port 8081

## Entrypoints

* Eureka: [http://127.0.0.1:8070](http://127.0.0.1:8070)
* Rest API products invoking product service: [http://127.0.0.1:8080/product](http://127.0.0.1:8080/product)
* Props from config server: [http://127.0.0.1:8080/test-property](http://127.0.0.1:8080/test-property)

## Additional Entrypoints when running locally

* Config Server: [http://127.0.0.1:8090](http://127.0.0.1:8090)
* product service: [http://127.0.0.1:8081/product](http://127.0.0.1:8081/product)
* Props from config server: [http://127.0.0.1:8081/test-property](http://127.0.0.1:8081/test-property)
* h2 console: [http://127.0.0.1:8081/h2-console](http://127.0.0.1:8081/h2-console)

## Build everything manually for compose

```bash
( cd services/configserver ; mvn clean spring-boot:build-image )
( cd services/eurekaserver ; mvn clean spring-boot:build-image )
( cd services/products ; mvn clean spring-boot:build-image )
( cd services/rest-api ; mvn clean spring-boot:build-image )
```
