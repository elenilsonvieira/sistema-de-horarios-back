FROM openjdk:11.0.10
VOLUME /tmp
COPY target/Sistema-de-Horarios-0.0.1-SNAPSHOT.jar Sistema-de-Horarios-0.0.1-SNAPSHOT.jar
CMD [ "java" , "-Djava.security.egd=file:/dev /./urandom" , "-jar" , "/Sistema-de-Horarios-0.0.1-SNAPSHOT.jar" ]