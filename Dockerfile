# Этап сборки
FROM eclipse-temurin:17-jdk-focal AS build
WORKDIR /app

# Copy Gradle files first for better caching
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Make Gradlew executable
RUN chmod +x ./gradlew

# Copy source code
COPY src src

# Build the application
RUN ./gradlew bootJar --no-daemon

# Финальный этап
FROM eclipse-temurin:17-jre-focal
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"] 