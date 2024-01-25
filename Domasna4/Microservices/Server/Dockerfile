FROM openjdk:17
EXPOSE 8761
ADD target/server-docker.jar server-docker.jar
ENTRYPOINT ["java", "-jar", "server-docker.jar"]