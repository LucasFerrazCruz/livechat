# Usa uma imagem Java 17 oficial
FROM eclipse-temurin:21-jdk

# Cria um diretório de trabalho dentro do container
WORKDIR /app

# Copia o JAR gerado para dentro do container
COPY target/livechat-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta usada pela aplicação
ENV PORT 8080
EXPOSE 8080

# Comando que inicia sua aplicação
CMD ["java", "-jar", "app.jar"]