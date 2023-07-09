FROM adoptopenjdk/openjdk11:alpine

WORKDIR /app

COPY target/servico-pagamento-0.0.1-SNAPSHOT.jar servico-pagamento.jar

EXPOSE 8080

CMD ["java", "-jar", "servico-pagamento.jar"]

