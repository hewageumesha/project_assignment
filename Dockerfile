# Use official OpenJDK runtime as base image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory inside container
WORKDIR /app

# Copy jar file into container
COPY target/assignment-0.0.1-SNAPSHOT.jar app.jar

# Expose application port
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java","-jar","app.jar"]