FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/CrudApp-1.0-SNAPSHOT.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
