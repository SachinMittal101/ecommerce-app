# base image
FROM openjdk:17-oracle

EXPOSE 8080

ADD target/service-registry-app.jar service-registry-app.jar

ENTRYPOINT ["java", "-jar", "service-registry-app.jar"]