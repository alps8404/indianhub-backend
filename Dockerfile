# 1. Use a base image with JDK to build the app
FROM eclipse-temurin:17-jdk AS build

# 2. Set working directory
WORKDIR /app

# 3. Copy source code into Docker container
COPY . .

# 4. Build Spring Boot JAR (skip tests for speed)
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# 5. Use a smaller image for running the app
FROM eclipse-temurin:17-jre

# 6. Set working directory for runtime
WORKDIR /app

# 7. Copy built JAR file into the final image
COPY --from=build /app/target/*.jar app.jar

# 8. Start the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
