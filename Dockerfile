FROM maven:3-openjdk-11 AS builder
COPY ./../src/ app/src/
COPY ./../pom.xml src
RUN mnv clean package

FROM openjdk:11
COPY --from=builder app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT java -Xmx512 -jar app.jar