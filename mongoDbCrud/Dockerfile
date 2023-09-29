FROM gradle:jdk17-alpine AS build

COPY --chown=gradle:gradle . /app
WORKDIR /app
RUN gradle build --no-daemon

FROM openjdk:17-alpine

COPY --from=build /app/build/libs/mongoDbCrud-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
EXPOSE 8080