# luam o imagine
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn -B depedency:go-offline

COPY src ./src
COPY database ./database

RUN mvn -B -Dskiptests package

# etapa 2: rularea aplicatiei
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
