FROM maven:latest AS build
WORKDIR /app-reviews
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests
FROM openjdk:latest
EXPOSE 9002
WORKDIR /app-reviews
COPY --from=build /app-reviews/target/reviews-app.jar .
CMD ["java", "-jar", "reviews-app.jar"]