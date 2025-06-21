# Use a small and efficient base Java image
FROM eclipse-temurin:17-jdk-alpine

# Set a working directory
WORKDIR /app

# Add the packaged jar file (after Maven build)
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Expose port (default Spring Boot port)
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
