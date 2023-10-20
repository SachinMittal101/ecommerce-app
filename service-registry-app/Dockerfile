# Use an official OpenJDK 17 runtime as a parent image
FROM openjdk:17-oracle

# Set the working directory to /app
WORKDIR /app

# Copy the JAR file into the container at /app
COPY target/service-registry-app-0.0.1-SNAPSHOT.jar /app/service-registry-app.jar

# Make port 8080 available to the world outside this container
EXPOSE 8761

# Define the command to run your application
CMD ["java", "-jar", "service-registry-app.jar"]