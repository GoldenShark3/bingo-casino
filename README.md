# Bingo casino

Educational project to learn how to work with microservice architecture and spring reactive.

A simple casino in which the user places a bet and wins with a 49% chance. <br/>

:heavy_exclamation_mark: Application without error checking. Made just to try use new technologies

## Technology:

+ Spring reactive (webflux)
+ Spring cloud stream (RabbitMQ)
+ Spring cloud netflix (Eureka)
+ Spring cloud gateway
+ R2DBC
+ Zipkin
+ Graylog
+ Docker

## How to start:

1. Open graylog-docker folder and run 'docker-compose up -d'
2. Go to localhost:9000 [Credentials: admin/admin] and create GELF UDP input
3. Open root directory and run 'docker-compose up -d'
4. Enjoy ☺️

## Applications urls:

1. localhost:15672 - RabbitMQ. [Credentials: guest/guest]
2. localhost:9411 - Zipkin.
3. localhost:8761 - Eureka.
4. localhost:8080 - Spring cloud gateway

# Project schema

![bing-casino](https://user-images.githubusercontent.com/72407856/173547208-43799483-153d-42a2-9b79-8c0679eb3ffc.png)


