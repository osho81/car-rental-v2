## Car Rental Application

### Intro on overall car rental project

This car-rental-v2 is mainly a rest api project built on Java/SpringBoot.
From the admin-web website, registered administrators/employees can retrieve, display and edit data from the
car-rental-v2 rest api endpoints and its connected database.
From the customer-web website, enables the similar functions for customers (non-administrators). Different access rights
etc. will be described hereafter.

Note: car-rental-v1 is almost identical to this car-rental-v2, but the latter is with optional microservices only
and used in the fullstack project)

#### All pojects belonging to the FULLSTACK car rental project can be found here:

- [car-rental-v2 (this backend rest api project)](https://gitlab.com/car-rental-fullstack/car-rental-v2)
- [admin-web (frontend js project)](https://gitlab.com/car-rental-fullstack/admin-web)
- [customer-web (corresponding webpage for customers)](https://gitlab.com/car-rental-fullstack/customer-web)
- Or see all related projects in the [GitLab project-group](https://gitlab.com/car-rental-fullstack)

##### Corporate styleguide used for these projects:

- [TW Styleguide](https://gitlab.com/car-rental-fullstack/tw-styleguide)

##### All projects belonging to the MICROSERVICE car rental, can be found here:

- The microservice
  aligned [car-rental-v1 (the V1 backend rest api project)](https://gitlab.com/car-rental-fullstack/car-rental-v1)
- Microservice [api-gateway](https://github.com/osho81/car-rental-api-gateway)
- Microservice for [rate exchange](https://github.com/osho81/car-rental-exchange-service)
- Server [registry](https://github.com/osho81/car-rental-service-registry) for the already available and eventual more
  future microservices

### Functions and api services

- Login with Keycloak security server (via postman or other client)
- Api for storing, updating, deleting new customers, cars, orders
- Api for exchange price from SEK to EUR

### Techs & languages used

- Java, Spring, Springboot, Rest-api
- H2 (while MySQl was used in car-rental-v1)
- Keycloak & spring security
- HTTP request to external api

### Security related matters for the fullstack project

For login and access management, [Keycloak (Quarkus distribution)](https://www.keycloak.org/downloads) is employed. For
the frontend (customer-web & admin-web) a Javascript adapter from the same source have been included in the project. Brief
configuration description:

- Realm name: car-rental-realm
- Client id: car-rental-v2
- Valid post logout redirect URIs, relevant for e.g. VS Code live server:
    - http://localhost:9090/api/v1/*
    - http://127.0.0.1:5500/*
    - http://127.0.0.1:5501/* (in case use customer & admin web simultanous)
    - During development * as uri and web origin uri is sufficient.
- Roles: admin & user

Authorization is validated using keycloak Bearer access token in the http requests.

- Users with assigned "admin" role can access the endpoints described for admins/employees
- Users with assigned "user" role can access the endpoints described for the customers
- The role restrictions can be seen in SecurityConfig.java (in its antMatchers)

### Purpose/Motivation

To test and improve skills in Springboot, security implementation, and microservice architecture.