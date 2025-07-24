# Etapa de build: usa Maven com Java 17
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa de runtime: usa JDK leve para rodar o .jar
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/e-commerce-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
