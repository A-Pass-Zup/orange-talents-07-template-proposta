FROM maven:3-openjdk-11 AS builder
COPY ./src/ app/src/
COPY ./pom.xml app/
ENV DATABASE_PLATFORM h2
ENV DATABASE_DRIVER org.h2.Driver
ENV DATABASE_URL jdbc:h2:mem:testdb
ENV DATABASE_USER root
ENV DATABASE_PASSWORD 12345
RUN /usr/bin/mvn -f app/ clean package

FROM openjdk:11
COPY --from=builder app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT java -jar app.jar