## use jdk
#FROM openjdk:23-jdk
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
# copy all source code into container
COPY . .

#COPY .env /app
# run command to build maven , have test -DskipTests
RUN mvn clean package

# using java sdk to run
FROM eclipse-temurin:23.0.2_7-jre-alpine-3.21

WORKDIR /app

# Copy file JAR đã build từ bước trước
COPY --from=build /app/target/*.jar app.jar

#COPY target/TuanHuy-0.0.1-SNAPSHOT.jar /app/target/TuanHuy-0.0.1-SNAPSHOT.jar
#ENTRYPOINT ["java","-jar","/app/target/TuanHuy-0.0.1-SNAPSHOT.jar","--server.port=9000"]
EXPOSE 9000

# Chạy ứng dụng Spring Boot
CMD ["java", "-jar", "app.jar"]