# Stage 1: Build the application using Maven
FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

# First copy just the POM file to cache dependencies
COPY pom.xml .
# Download dependencies (this layer will be cached unless pom.xml changes)
RUN mvn dependency:go-offline -B

# Then copy source files
COPY src ./src
# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Copy the built JAR file (use exact name for reliability)
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8000

ENTRYPOINT ["java", "-jar", "app.jar"]