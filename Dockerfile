FROM openjdk:17-jdk-alpine
WORKDIR /server
COPY target/etech-1.0.jar /server
COPY ./src/main/resources/static/images /server/src/main/resources/static/images
ENTRYPOINT ["java", "-jar", "etech-1.0.jar"]