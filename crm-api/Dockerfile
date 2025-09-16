# Use Java 17 JDK for Spring Boot
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy the jar built by Maven/Gradle
COPY target/*.jar app.jar

# Expose port your Spring Boot app runs on
EXPOSE 8080

# Run the app
ENTRYPOINT ["java","-jar","app.jar"]
