#FROM eclipse-temurin:21-jdk-alpine
#WORKDIR /app
#COPY target/TuanHuy-0.0.1-SNAPSHOT.jar /app/app.jar
#EXPOSE 9000
#ENTRYPOINT ["java", "-jar", "/app/app.jar","--server.port=9000","--spring.profiles.active=dev"]
#

##----------------------------------------------------------------------------------------------------------------


# Stage 1: Build ứng dụng Spring Boot
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY . /app
RUN mvn clean package -DskipTests

# Stage 2: Chạy ứng dụng Spring Boot
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar /app/app.jar
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "/app/app.jar", "--server.port=9000", "--spring.profiles.active=dev"]
