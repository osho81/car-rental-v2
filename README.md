## Car Rental Application

### Intro on overall car rental project
car-rental-v2 is mainly a rest api project built on Java/SpringBoot. 
From the admin-web website, registered administrators/employees can retrieve, display and edit data from the car-rental-v2 rest api endpoints and its connected database.
From the customer-web website, enables the similar functions for customers (non-administrators). Different access rights etc. will be described hereafter. 

#### The projects can be found here:
-  [car-rental-v2 (this backend rest api project)](https://gitlab.com/car-rental-fullstack/car-rental-v2)
-  [admin-web (frontend js project)](https://gitlab.com/car-rental-fullstack/admin-web)
-  [customer-web (corresponding webpage for customers)](https://gitlab.com/car-rental-fullstack/customer-web)

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