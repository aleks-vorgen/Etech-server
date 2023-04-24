FROM openjdk:17-jdk-alpine
WORKDIR /server
COPY target/etech-1.0.jar /server
ENTRYPOINT ["java", "-jar", "etech-1.0.jar"]