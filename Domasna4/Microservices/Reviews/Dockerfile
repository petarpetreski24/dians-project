FROM openjdk:17
EXPOSE 9001
ADD target/review-docker.jar review-docker.jar
ENTRYPOINT ["java", "-jar", "/review-docker.jar"]