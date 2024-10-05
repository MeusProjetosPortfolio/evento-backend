# Use uma imagem base do OpenJDK
FROM openjdk:21-jdk-slim

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o arquivo JAR do seu projeto para o contêiner
COPY target/registro-0.0.1-SNAPSHOT.jar app.jar

# Exponha a porta que sua aplicação usará
EXPOSE 8080

# Configure o comando de inicialização do contêiner
ENTRYPOINT ["java", "-jar", "app.jar"]
