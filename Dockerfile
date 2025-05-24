# Используем официальный образ OpenJDK
FROM openjdk:17-jdk-slim
WORKDIR /app

# Копируем jar-файл (сначала соберите проект: ./gradlew bootJar)
COPY build/libs/*.jar app.jar

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "app.jar"] 