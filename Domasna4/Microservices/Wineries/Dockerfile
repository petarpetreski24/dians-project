FROM openjdk:17
EXPOSE 9003
ADD target/winery-docker.jar winery-docker.jar
ENTRYPOINT ["java", "-jar", "/winery-docker.jar"]