# define base docker image
FROM openjdk:17
LABEL maintainer="javaguides.net"
ADD target/practice-0.0.1-SNAPSHOT.jar practice-docker-demo.jar
ENTRYPOINT ["java", "-jar", "practice-docker-demo.jar"]