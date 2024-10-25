# Use the official Maven image to build the app
FROM maven:3.8.3-openjdk-17 AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the application code
COPY src ./src

# Build the application
RUN mvn package -DskipTests

# Use a smaller image for the final deployment
FROM openjdk:17-jdk-slim

# Set the working directory in the final image
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Set the entry point for the container
ENTRYPOINT ["java", "-jar", "app.jar"]
