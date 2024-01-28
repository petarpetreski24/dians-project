FROM maven:latest AS build
WORKDIR /server
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests
FROM openjdk:latest
EXPOSE 8761
WORKDIR /server
COPY --from=build /server/target/server-app.jar .
CMD ["java", "-jar", "server-app.jar"]