# base image
FROM openjdk:17-oracle

# Set the build argument for the JAR version (default to 0.0.1-SNAPSHOT)
ARG JAR_VERSION=0.0.1-SNAPSHOT
WORKDIR /app

# Copy the JAR file into the container at /app
COPY target/sci-service-${JAR_VERSION}.jar /app

# PORT
EXPOSE 8080

# Define the command to run your application
CMD ["java", "-jar", "app.jar"]