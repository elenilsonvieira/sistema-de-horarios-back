FROM maven:3.6.0-jdk-11-slim AS build
WORKDIR /Sistema_de_Horarios
COPY . .
RUN mvn -f pom.xml clean package

ENTRYPOINT ["sh", "-c", "java -jar target/Sistema-de-Horarios-0.0.1-SNAPSHOT.jar"]