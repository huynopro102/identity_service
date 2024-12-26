## use jdk
FROM openjdk:23-jdk
WORKDIR /app
COPY .env /app
COPY target/TuanHuy-0.0.1-SNAPSHOT.jar /app/target/TuanHuy-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app/target/TuanHuy-0.0.1-SNAPSHOT.jar","--server.port=9000"]
EXPOSE 9000