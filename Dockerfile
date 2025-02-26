## use jdk
#FROM openjdk:23-jdk
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml ./
RUN mvn dependency:go-offline # cache dependency

# copy all source code into container
COPY . .

#COPY .env /app
# run command to build maven , have test -DskipTests
RUN mvn clean package -DskipTests

# using java sdk to run
FROM eclipse-temurin:21-jre-alpine AS runtime

WORKDIR /app

# Copy file JAR đã build từ bước trước
COPY --from=build /app/target/*.jar app.jar

#COPY target/TuanHuy-0.0.1-SNAPSHOT.jar /app/target/TuanHuy-0.0.1-SNAPSHOT.jar
#ENTRYPOINT ["java","-jar","/app/target/TuanHuy-0.0.1-SNAPSHOT.jar","--server.port=9000"]
EXPOSE 9000

# Chạy ứng dụng Spring Boot
CMD ["java", "-jar", "app.jar"]