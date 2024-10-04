# Usar uma imagem base do JDK
FROM openjdk:21-jdk-slim

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar o arquivo JAR gerado para o diretório de trabalho
COPY target/registro-0.0.1-SNAPSHOT.jar app.jar

# Expor a porta que a aplicação irá utilizar
EXPOSE 8080

# Comando para rodar o JAR da aplicação
CMD ["java", "-jar", "app.jar"]
