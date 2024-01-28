FROM maven:latest AS build
WORKDIR /app-winery
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests
FROM openjdk:latest
EXPOSE 9005
WORKDIR /app-winery
COPY --from=build /app-winery/target/winery-app.jar .
CMD ["java", "-jar", "winery-app.jar"]