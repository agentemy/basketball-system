FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY target/player-service-3.2.0.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]