# Use Maven 3.9.9 as the builder image
FROM maven:3.9.9 AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project's POM file
COPY pom.xml /app/pom.xml

# Copy the source code
COPY src /app/src

# Build the application, skipping tests
RUN mvn clean package -DskipTests

# Use OpenJDK 21 as the runtime image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=builder /app/target/*.jar /app/app.jar

# Define the command to run the application
CMD ["java", "-jar", "app.jar"]
