# Fase de construção usando a imagem do Maven com JDK 17
FROM maven:3.8.4-openjdk-17 AS build

# Copiar o código fonte e o pom.xml
COPY src /app/src
COPY pom.xml /app

# Definir o diretório de trabalho
WORKDIR /app

# Executar o Maven para construir o projeto e gerar o .jar
RUN mvn clean install -DskipTests

# Fase de execução usando a imagem do OpenJDK 17 com Alpine
FROM eclipse-temurin:17-jre-alpine

# Copiar o .jar gerado da fase de construção
COPY --from=build /app/target/catalog-0.0.1-SNAPSHOT.jar /app/app.jar

# Definir o diretório de trabalho
WORKDIR /app

# Expor a porta onde a aplicação será executada
EXPOSE 8080

# Comando para rodar a aplicação
CMD ["java", "-jar", "app.jar"]
