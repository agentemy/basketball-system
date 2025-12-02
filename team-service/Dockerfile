FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY target/team-service-3.2.0.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]