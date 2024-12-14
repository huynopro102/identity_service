## use jdk
FROM openjdk:23-jdk
WORKDIR /app
COPY target/TuanHuy-0.0.1-SNAPSHOT.jar /app/target/TuanHuy-0.0.1-SNAPSHOT.jar
COPY .env /app/.env
ENTRYPOINT ["java","-jar","/app/target/TuanHuy-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080


