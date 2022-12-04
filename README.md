## Car Rental Application

### Description
Springboot project, simulating a car booking/rental system. 
With persistent database storage, security implementation.

##### It is the main app of a wider car rental service project, which consists of:
- This app that makes up the logic of the rental system
- Microservice [api-gateway](https://github.com/osho81/car-rental-api-gateway)
- Microservice for [rate exchange](https://github.com/osho81/car-rental-exchange-service)
- Server [registry](https://github.com/osho81/car-rental-service-registry) for the already available and eventual more future microservices


### Functions etc
- Login with Keycloak security server
- Storing, updating, deleting new customers, cars, orders
- Exchange price from SEK to EUR

### Techs & languages used
- Java, Spring, Springboot, - Rest-api
- JPA, MySQL, MySql-Workbench
- Keycloak & spring security
- HTTP request to external api

### Purpose/Motivation
To test and improve skills in Springboot, security implementation, and microservice architecture.