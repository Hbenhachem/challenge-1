# Use an official OpenJDK 17 runtime as the base image
FROM adoptopenjdk/openjdk17:alpine-jre

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file into the container at /app
COPY target/my-spring-boot-app.jar /app

# Expose the port that the Spring Boot application listens on
EXPOSE 8080

# Set the entrypoint command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "application.jar"]
