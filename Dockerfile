# Этап сборки
FROM eclipse-temurin:17-jdk-focal AS build
WORKDIR /app
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew bootJar --no-daemon

# Финальный этап
FROM eclipse-temurin:17-jre-focal
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"] 