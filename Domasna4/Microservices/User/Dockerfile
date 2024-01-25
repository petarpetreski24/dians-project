FROM openjdk:17
EXPOSE 9002
ADD target/user-docker.jar user-docker.jar
ENTRYPOINT ["java", "-jar", "/user-docker.jar"]