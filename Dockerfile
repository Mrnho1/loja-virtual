# Etapa 1: Build da aplicação com Maven
FROM ubuntu:latest AS builder

RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven

WORKDIR /app
COPY . .

RUN mvn clean package -DskipTests

# Etapa 2: Imagem final, mais leve
FROM ubuntu:latest

RUN apt-get update && \
    apt-get install -y openjdk-17-jdk

WORKDIR /app

# Copia o JAR gerado do estágio anterior
COPY --from=builder /app/target/e-commerce-1.0.0.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]