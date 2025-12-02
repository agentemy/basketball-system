FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY target/match-service-3.2.0.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app.jar"]