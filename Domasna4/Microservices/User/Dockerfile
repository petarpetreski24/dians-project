FROM maven:latest AS build
WORKDIR /app-user
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests
FROM openjdk:latest
EXPOSE 9003
WORKDIR /app-user
COPY --from=build /app-user/target/user-app.jar .
CMD ["java", "-jar", "user-app.jar"]