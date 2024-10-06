# Use a imagem oficial do OpenJDK como base
FROM openjdk:21-jre-slim

# Defina o diretório de trabalho
WORKDIR /app

# Copie o arquivo JAR da sua aplicação para o container
COPY target/registro-0.0.1-SNAPSHOT.jar app.jar

# Exponha a porta que o aplicativo irá rodar
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
