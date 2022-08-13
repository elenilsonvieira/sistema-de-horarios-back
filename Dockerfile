#FROM openjdk:11.0.10
#VOLUME /tmp
#COPY target/Sistema-de-Horarios-0.0.1-SNAPSHOT.jar Sistema-de-Horarios-0.0.1-SNAPSHOT.jar
#CMD [ "java" , "-Djava.security.egd=file:/dev /./urandom" , "-jar" , "/Sistema-de-Horarios-0.0.1-SNAPSHOT.jar" ]

#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/Sistema-de-Horarios-0.0.1-SNAPSHOT.jar /usr/local/lib/Sistema-de-Horarios.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/Sistema-de-Horarios.jar"]
