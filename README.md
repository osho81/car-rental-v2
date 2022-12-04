## Car Rental Application

### Description
Springboot project, the main app in car rental system. 
With persistent database storage, security implementation.

##### It is the main app of a wider car rental service project, which consists of:
- This app that makes up the logic of the rental system
- Microservice [api-gateway](https://github.com/osho81/car-rental-api-gateway)
- Microservice for [rate exchange](https://github.com/osho81/car-rental-exchange-service)
- Server [registry](https://github.com/osho81/car-rental-service-registry) for the already available and eventual more future microservices
- Or see all related projects in the [GitLab project-group](https://gitlab.com/car-rental-fullstack) 
  
### Functions etc
- Login with Keycloak security server
- Storing, updating, deleting new customers, cars, orders
- Exchange price from SEK to EUR

### Techs & languages used
- Java, Spring, Springboot, Rest-api
- H2 (while MySQl was used in car-rental-v1)
- Keycloak & spring security
- HTTP request to external api

### Purpose/Motivation
To test and improve skills in Springboot, security implementation, and microservice architecture.