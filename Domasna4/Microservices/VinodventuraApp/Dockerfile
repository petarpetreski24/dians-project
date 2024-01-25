FROM openjdk:17
EXPOSE 9005
ADD target/web-app-docker.jar web-app-docker.jar
ENTRYPOINT ["java", "-jar", "/web-app-docker.jar"]