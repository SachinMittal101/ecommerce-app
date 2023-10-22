# base image
FROM openjdk:17-oracle

EXPOSE 8080

ADD target/sci-service.jar sci-service.jar

ENTRYPOINT ["java", "-jar", "sci-service.jar"]