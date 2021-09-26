# Spring Cloud Microservices sample project

* Microservices architecture
* Eureka Discovery
* Cloud Config Server with Encryption

<!-- markdownlint-disable MD036 -->
**Single code base just for test simplicity**
<!-- markdownlint-enable MD036 -->

## Start via Docker Compose

Run one of:

1. `docker compose -f docker/docker-compose-dev.yml up`
2. `ENCRYPT_KEY=MY_SUPER_SECRET_PASSWORD docker compose -f docker/docker-compose-prod.yml up`

## Info

* config server on port 8090
* eureka server on port 8070
* rest-api on port 8080
* products on port 8081

## How to build everything manually for compose

```bash
( cd services/configserver ; mvn clean spring-boot:build-image )
( cd services/eurekaserver ; mvn clean spring-boot:build-image )
( cd services/products ; mvn clean spring-boot:build-image )
( cd services/rest-api ; mvn clean spring-boot:build-image )
```
