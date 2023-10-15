# ecommerce-app

# service-registry-app
This service is an Eureka server where other services will register and discover themselves for communication

# sci-service
SCI stands for Service Connector Interface which is a cloud api-gateway which will parse every request going to order and product service.
This service acts as a Eureka client and is registered on the Service Registry App (Eureka Server).

# auth-service
auth-service will be used to register user in database. It also allows creation of JWT token based on registered users
only.It also exposes one endpoint which will be used to validate token.
This service acts as a Eureka client and is registered on the Service Registry App (Eureka Server).

# order-service
The Order Service is responsible for managing order operations. This service acts as a Eureka client and is registered on the Service Registry App (Eureka Server).
It also interacts with Product service via Eureka to fetch product details.

# product-service
The Product Service is responsible for managing order operations.This service acts as a Eureka client and is registered on the Service Registry App (Eureka Server).

