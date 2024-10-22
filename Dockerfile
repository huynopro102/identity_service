# use jdk
FROM openjdk:22-jdk
WORKDIR /app
COPY target/TuanHuy-0.0.1-SNAPSHOT.jar /app/target/TuanHuy-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app/target/TuanHuy-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080
