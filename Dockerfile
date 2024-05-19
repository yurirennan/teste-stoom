# stage:build app in docker
FROM maven:3.5.2-jdk-8 AS build
COPY src /opt/app/src
COPY pom.xml /opt/app
RUN mvn -f /opt/app/pom.xml clean package -DskipTests

# stage:build image
FROM openjdk:8-jre-slim
COPY --from=build /opt/app/target/app.jar /app/app.jar
ENTRYPOINT exec java -jar /app/app.jar