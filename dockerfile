## Builder Image
#FROM maven:3.8.5-openjdk-17 AS builder
#COPY src /usr/src/app/src
#COPY pom.xml /usr/src/app
#RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests

## Runner Image
FROM openjdk:17
COPY target/*.jar /usr/app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/app.jar"]