# Spring Cloud Microservices sample project

* Microservices architecture
* Eureka Discovery
* Cloud Config Server with Encryption

## Start via Docker Compose

Run one of:

1. `docker-compose -f docker/docker-compose-dev.yml up`
2. `ENCRYPT_KEY=MY_SUPER_SECRET_PASSWORD docker-compose -f docker/docker-compose-prod.yml up`

## Info

* config server on port 8090
* eureka server on port 8070
* client-1 on port 8080

## How to build everything manually for compose

```bash
( cd services/configserver ; mvn spring-boot:build-image )
( cd services/eurekaserver ; mvn spring-boot:build-image )
( cd services/client-1 ; mvn spring-boot:build-image )
```

## Publish images to docker hub

```bash
docker push paolodenti/configserver:latest
docker push paolodenti/eurekaserver:latest
docker push paolodenti/client-1:latest
```
