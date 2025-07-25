# Etapa 1: Compilar o projeto
FROM ubuntu:latest AS build

RUN apt-get update && apt-get install -y openjdk-17-jdk maven

WORKDIR /app
COPY . .

RUN mvn clean package -DskipTests

# Etapa 2: Imagem final com o JAR
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copia o JAR da etapa de build
COPY --from=build /app/target/e-commerce-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]